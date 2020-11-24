# Groovy & Spring Boot

-----

#### Criando o seu projeto com o Sring Initializr

Vamos criar o nosso esqueleto! Usando o **Spring Initializr** [https://start.spring.io/](https://start.spring.io/), crie o projeto com os seguintes parâmetros:

- Defina a linguagem para **Groovy**.
- Escolha a opção **Gradle Project**.
- Escolha a versão **2.4.0** do Spring Boot.
- Defina o **Group** e o **Artifact** como achar melhor. Eu sugiro você investir alguns minutos pesquisando sobre qual a melhor maneira para definir um pacote em Java. (Vide [https://bit.ly/2BLM8CZ](https://bit.ly/2BLM8CZ))
- Adicione as dependências: **Spring Web, Spring Data JPA e MySQL Driver.**

Pronto, basta confirmar e baixar o .zip do projeto criado e já conseguimos a nossa primeira versão compilável.

-----

#### Configurando o nosso banco de dados MySQL

A configuração do BD no Spring é algo bem simples. Vamos configurar o `application.properties` para permitir que você crie suas tabelas automaticamente, utilizando a abordagem Code First. 

Para isso, você pode seguir o seguinte passo-a-passo:

- Primeiro crie seu schema no MySql.

```bash
CREATE SCHEMA `groovyboot` ;
```

- Agora, configure o `application.properties` da seguinte maneira:

    ```yaml
    spring.datasource.url = jdbc:mysql://localhost/<seu-schema>?useSSL=false&allowPublicKeyRetrieval=true
    spring.datasource.username = <username do seu banco>
    spring.datasource.password = <senha do seu banco>
    ```

Para definirmos que o hibernate deve efetuar as atualizações sempre que alguma modificação ocorrer em nossas domains no projeto, utilizaremos a propriedade `spring.jpa.hibernate.ddl-auto`.

```yaml
spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.MySQL5InnoDBDialect
spring.jpa.hibernate.ddl-auto = update
```

Basicamente é isso, por padrão o Spring irá definir a maioria das configurações e isso é o suficiente para este projeto. 

-----

#### Criando nossas classes de domínio

Para a criação de nossas classes de domínio, iremos precisar de uma BaseEntity. A BaseEntity irá conter algumas definições padrões em nossos sitema.

Com isso, crie o seguinte package: `com.pitzdev.groovyboot.domain.base`

```groovy
package com.pitzdev.groovyboot.domain.base

import com.sun.istack.NotNull
import com.sun.istack.Nullable
import groovy.transform.Sortable
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate

import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.MappedSuperclass

@Sortable
@MappedSuperclass
abstract class BaseEntity implements Comparable<BaseEntity> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id

    @Nullable
    @LastModifiedDate
    Date lastUpdated

    @NotNull
    @CreatedDate
    Date dateCreated

    @NotNull
    Boolean deleted = false

    @Override int compareTo(BaseEntity o) {
        return a <=> o.a
    }
}
```

Um ponto importante aqui, o Spring Boot nos permite duas formas de definir se uma coluna pode ser nula ou não: Com @NotNull ou com @Column(nullable = false). Você pode ler e entender o que te atende melhor aqui ([https://thorben-janssen.com/hibernate-tips-whats-the-difference-between-column-nullable-false-and-notnull/](https://thorben-janssen.com/hibernate-tips-whats-the-difference-between-column-nullable-false-and-notnull/)).

Com o nosso BaseEntity criado, podemos dar vida ao restante dos domínios deste projeto: Holder e Card.

```groovy
package com.pitzdev.groovyboot.domain.holder

import com.pitzdev.groovyboot.domain.base.BaseEntity
import com.sun.istack.NotNull

import javax.persistence.Entity

@Entity
class Holder extends BaseEntity {

    @NotNull
    String name

    @NotNull
    String cpfCnpj

    @NotNull
    Date birthDate

    @NotNull
    String email

}
```

```groovy
package com.pitzdev.groovyboot.domain.card

import com.pitzdev.groovyboot.domain.base.BaseEntity
import com.pitzdev.groovyboot.domain.holder.Holder
import com.sun.istack.NotNull

import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "card")
class Card extends BaseEntity {

    @NotNull
    String maskedNumber

    @NotNull
    Integer expirationMonth

    @NotNull
    Integer expirationYear

    @ManyToOne
    @JoinColumn(name = "holder_id", referencedColumnName = "id", nullable = false)
    Holder holder

}
```
-----

#### Acessando o nosso banco de dados com repositórios

Com os nossos domínios criados, iremos precisar de nossos **Repositories.** O repository será responsável por acessar os dados em nosso banco de dados.

Com o uso do JPA, a criação de um repository - por hora - é bem simples: 

```groovy
package com.pitzdev.groovyboot.repository.card

import com.pitzdev.groovyboot.domain.card.Card
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CardRepository extends JpaRepository<Card, Long> {

    List<Card> findAll()

    Optional<Card> findById(Long id)

}
```

```groovy
package com.pitzdev.groovyboot.repository.holder

import com.pitzdev.groovyboot.domain.holder.Holder
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface HolderRepository extends JpaRepository<Holder, Long> {

    List<Holder> findAll()

    Optional<Holder> findById(Long id)

}
```

Em breve iremos incrementar estes caras.

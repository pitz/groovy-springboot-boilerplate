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

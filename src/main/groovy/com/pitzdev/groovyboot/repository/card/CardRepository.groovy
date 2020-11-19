package com.pitzdev.groovyboot.repository.card

import com.pitzdev.groovyboot.domain.card.Card
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CardRepository extends JpaRepository<Card, Long> {

    List<Card> findAll()

    Optional<Card> findById(Long id)

}

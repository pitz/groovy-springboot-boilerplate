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

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
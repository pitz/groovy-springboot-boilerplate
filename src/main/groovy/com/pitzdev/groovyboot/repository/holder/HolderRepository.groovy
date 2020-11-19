package com.pitzdev.groovyboot.repository.holder

import com.pitzdev.groovyboot.domain.holder.Holder
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface HolderRepository extends JpaRepository<Holder, Long> {

    List<Holder> findAll()

    Optional<Holder> findById(Long id)

}

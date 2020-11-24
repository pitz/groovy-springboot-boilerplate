package com.pitzdev.groovyboot.service.holder

import com.pitzdev.groovyboot.domain.holder.Holder
import com.pitzdev.groovyboot.repository.holder.HolderRepository
import com.pitzdev.groovyboot.utils.CustomDateUtils

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class HolderServiceImpl implements HolderService {

    @Autowired
    private final HolderRepository holderRepository

    public List<Holder> getHolderList() {
        return holderRepository.findAll()
    }

    public Holder getHolder(Long id) {
        Optional<Holder> holder = holderRepository.findById(id)
        if (holder.isPresent()) return holder.get()
        return null
    }

    public Holder save(Map holderInfo) {
        Holder holder = new Holder()
        holder.name = holderInfo.name
        holder.cpfCnpj = holderInfo.cpfCnpj
        holder.email = holderInfo.email
        holder.birthDate = CustomDateUtils.toDate(holderInfo.birthDate)
        holder.dateCreated = new Date()
        holder = holderRepository.save(holder)
        return holder
    }
}
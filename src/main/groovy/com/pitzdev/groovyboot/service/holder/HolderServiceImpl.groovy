package com.pitzdev.groovyboot.service.holder

import com.pitzdev.groovyboot.domain.holder.Holder
import com.pitzdev.groovyboot.repository.holder.HolderRepository
import org.springframework.stereotype.Service

@Service
class HolderServiceImpl implements HolderService {

    HolderRepository holderRepository

    public List<Holder> getHolderList() {
        return holderRepository.findAll {}.toList()
    }

    public Holder getHolder(Long id) {
        return holderRepository.findById(id)
    }

    public Holder save(Map holderInfo) {
        Holder holder = new Holder()
        holder.name = holderInfo.name
        holder.cpfCnpj = holderInfo.cpfCnpj
        holder.email = holderInfo.email
        holder.birthDate = holderInfo.birthDate

        holderRepository.save(holder)
        // Implementar regras.
    }

}
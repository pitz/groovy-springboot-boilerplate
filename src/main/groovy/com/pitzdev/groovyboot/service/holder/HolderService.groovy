package com.pitzdev.groovyboot.service.holder

import com.pitzdev.groovyboot.domain.holder.Holder

interface HolderService {

    public List<Holder> getHolderList()

    public Holder getHolder(Long id)

    public Holder save(Map holderInfo)

}

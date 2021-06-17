package com.example.lawerapp.Utils

interface EntityMaper <Entity,DomainModel>{
    fun mapFromEntity(entity: Entity) :DomainModel

    fun mapToEntity(domainModel: DomainModel) :Entity
}
package com.example.covide19app.Utils

interface EntityMaper <Entity,DomainModel>{
    fun mapFromEntity(entity: Entity) :DomainModel

    fun mapToEntity(domainModel: DomainModel) :Entity
}
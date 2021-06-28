package com.example.covide19app.Retrofit.EntityMapper

import com.example.covide19app.Model.AdvicesModel
import com.example.covide19app.Model.CountriesModel
import com.example.covide19app.Retrofit.Entities.AdvicesEntity
import com.example.covide19app.Retrofit.Entities.CountryEntity
import com.example.covide19app.Utils.EntityMaper
import javax.inject.Inject

class AdvicesMapper @Inject constructor() : EntityMaper<AdvicesEntity, AdvicesModel> {
    override fun mapFromEntity(entity: AdvicesEntity): AdvicesModel {
        return AdvicesModel(
                title = entity.title,
                topic = entity.topic,
                image = entity.image
        )
    }

    override fun mapToEntity(domainModel: AdvicesModel): AdvicesEntity {
        return AdvicesEntity(
                title = domainModel.title,
                topic = domainModel.topic,
                image = domainModel.image
        )
    }
    fun mapFromEntityList(entities:List<AdvicesEntity>):List<AdvicesModel> {
        return entities.map { mapFromEntity(it) }
    }
}
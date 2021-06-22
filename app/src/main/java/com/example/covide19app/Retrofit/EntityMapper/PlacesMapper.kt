package com.example.covide19app.Retrofit.EntityMapper

import com.example.covide19app.Model.PlacesModel
import com.example.covide19app.Retrofit.Entities.PlacesEntity
import com.example.covide19app.Retrofit.Entities.PlacesRetrofitEntity
import com.example.covide19app.Utils.EntityMaper
import javax.inject.Inject

class PlacesMapper @Inject constructor() : EntityMaper<PlacesEntity, PlacesModel> {
    override fun mapFromEntity(retrofitEntity: PlacesEntity): PlacesModel {
       return PlacesModel(
               lang = retrofitEntity.lang,
               long = retrofitEntity.long,
               type = retrofitEntity.type,
               address = retrofitEntity.address,
               name = retrofitEntity.name,
               status = retrofitEntity.status,
               phone = retrofitEntity.phone,
       )
    }

    override fun mapToEntity(domainModel: PlacesModel): PlacesEntity {
        return PlacesEntity(
                lang = domainModel.lang,
                long = domainModel.long,
                type = domainModel.type,
                address = domainModel.address,
                name = domainModel.name,
                status = domainModel.status,
                phone = domainModel.phone,
        )
    }
    fun mapEntityListToModel(entites :List<PlacesEntity>):List<PlacesModel>{
        return entites.map { mapFromEntity(it) }
    }
}
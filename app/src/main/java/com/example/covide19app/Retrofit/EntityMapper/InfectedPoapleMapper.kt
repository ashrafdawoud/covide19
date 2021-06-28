package com.example.covide19app.Retrofit.EntityMapper

import com.example.covide19app.Model.InfectedPoapleModel
import com.example.covide19app.Model.PlacesModel
import com.example.covide19app.Retrofit.Entities.InfectedPoapleEntity
import com.example.covide19app.Retrofit.Entities.PlacesEntity
import com.example.covide19app.Utils.EntityMaper
import javax.inject.Inject

class InfectedPoapleMapper @Inject constructor() :EntityMaper<InfectedPoapleEntity,InfectedPoapleModel> {
    override fun mapFromEntity(entity: InfectedPoapleEntity): InfectedPoapleModel {
        return InfectedPoapleModel(
                objectId = entity.objectId,
                gander = entity.gander,
                symptoms = entity.symptoms,
                contant_number = entity.contant_number,
                userid = entity.userid,
                age = entity.age,
                lat = entity.lat,
                long = entity.long,
        )
    }

    override fun mapToEntity(domainModel: InfectedPoapleModel): InfectedPoapleEntity {
        return InfectedPoapleEntity(
                objectId = domainModel.objectId!!,
                gander = domainModel.gander!!,
                symptoms = domainModel.symptoms!!,
                contant_number = domainModel.contant_number!!,
                userid = domainModel.userid!!,
                age = domainModel.age!!,
                lat = domainModel.lat!!,
                long = domainModel.long!!,
        )
    }
    fun mapEntityListToModel(entites :List<InfectedPoapleEntity>):List<InfectedPoapleModel>{
        return entites.map { mapFromEntity(it) }
    }
}
package com.example.covide19app.Retrofit.EntityMapper

import com.example.covide19app.Model.DoctorsModel
import com.example.covide19app.Retrofit.Entities.PopularLawersRetrofitEntity
import com.example.covide19app.Retrofit.Entities.PopularLawersitEntity
import com.example.covide19app.Utils.EntityMaper
import javax.inject.Inject

class DoctorsMaper @Inject constructor() : EntityMaper<PopularLawersitEntity,DoctorsModel> {
    override fun mapFromEntity(entity: PopularLawersitEntity): DoctorsModel {
        return DoctorsModel(
            objectId = entity.objectId,
            name = entity.name,
            address = entity.address,
            exp = entity.exp,
            discreiption = entity.discreiption,
            image = entity.image,
            long = entity.long,
            lat = entity.lat,
        )
    }

    override fun mapToEntity(domainModel: DoctorsModel): PopularLawersitEntity {
        return PopularLawersitEntity(
            objectId = domainModel.objectId,
            name = domainModel.name,
            address = domainModel.address,
            exp = domainModel.exp,
            discreiption = domainModel.discreiption,
            image = domainModel.image,
            long = domainModel.long,
            lat = domainModel.lat,
        )
    }
    fun mapfromEntityList(entity: List<PopularLawersitEntity>) :List<DoctorsModel>{
        return entity.map { mapFromEntity(it) }
    }
}
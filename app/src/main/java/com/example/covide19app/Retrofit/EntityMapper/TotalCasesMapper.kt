package com.example.covide19app.Retrofit.EntityMapper

import com.example.covide19app.Model.TotalCaseModel
import com.example.covide19app.Retrofit.Entities.TotalCaseEntity
import com.example.covide19app.Utils.EntityMaper
import javax.inject.Inject

class TotalCasesMapper @Inject constructor() :EntityMaper<TotalCaseEntity,TotalCaseModel> {
    override fun mapFromEntity(entity: TotalCaseEntity): TotalCaseModel {
        return TotalCaseModel(
               country =  entity.country,
               confirmed =  entity.confirmed,
               recovered =  entity.recovered,
                critical = entity.critical,
                deaths = entity.deaths,
        )
    }

    override fun mapToEntity(domainModel: TotalCaseModel): TotalCaseEntity {
        return TotalCaseEntity(
                country =  domainModel.country,
                confirmed =  domainModel.confirmed,
                recovered =  domainModel.recovered,
                critical = domainModel.critical,
                deaths = domainModel.deaths,
        )
    }
    fun  mapFromEntityToList(entities : List<TotalCaseEntity>):List<TotalCaseModel>{
        return entities.map { mapFromEntity(it) }
    }
}
package com.example.covide19app.Retrofit.EntityMapper

import com.example.covide19app.Model.CountriesModel
import com.example.covide19app.Retrofit.Entities.CountryEntity
import com.example.covide19app.Utils.EntityMaper
import javax.inject.Inject

class CountryMapper @Inject constructor() : EntityMaper<CountryEntity, CountriesModel> {
    override fun mapFromEntity(entity: CountryEntity): CountriesModel {
        return CountriesModel(
                Country_text = entity.Country_text,
                Last_Update = entity.Last_Update,
                New_Cases_text = entity.New_Cases_text,
                New_Deaths_text = entity.New_Deaths_text,
                Total_Cases_text = entity.Total_Cases_text,
                Total_Deaths_text = entity.Total_Deaths_text,
                Total_Recovered_text = entity.Total_Recovered_text
        )
    }

    override fun mapToEntity(domainModel: CountriesModel): CountryEntity {
        return CountryEntity(
                Country_text = domainModel.Country_text!!,
                Last_Update = domainModel.Last_Update!!,
                New_Cases_text = domainModel.New_Cases_text!!,
                New_Deaths_text = domainModel.New_Deaths_text!!,
                Total_Cases_text = domainModel.Total_Cases_text!!,
                Total_Deaths_text = domainModel.Total_Deaths_text!!,
                Total_Recovered_text = domainModel.Total_Recovered_text!!
        )
    }

    fun entityListToModel(enities: List<CountryEntity>): List<CountriesModel> {
        return enities.map { mapFromEntity(it) }
    }
}
package com.example.covide19app.Repository

import com.example.covide19app.Model.CountriesModel
import com.example.covide19app.Retrofit.CountryRetrofitInterface
import com.example.covide19app.Retrofit.EntityMapper.CountryMapper
import com.example.covide19app.Utils.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CountriesRepository constructor(
        val countryRetrofitInterface: CountryRetrofitInterface,
        val countryMapper: CountryMapper
) {
    fun getCountryData() : Flow<DataState<List<CountriesModel>>> = flow{
        emit(DataState.Loading)
        try {
            val allCountryNetwork = countryRetrofitInterface.getCountryData()
            val allcountryModels= countryMapper.entityListToModel(allCountryNetwork)
            emit(DataState.Success(allcountryModels))
        }catch (e:Exception){
            emit(DataState.Error(e))
        }

    }
}
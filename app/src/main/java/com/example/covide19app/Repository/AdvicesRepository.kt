package com.example.covide19app.Repository

import com.example.covide19app.Model.AdvicesModel
import com.example.covide19app.Model.CountriesModel
import com.example.covide19app.Retrofit.CountryRetrofitInterface
import com.example.covide19app.Retrofit.EntityMapper.AdvicesMapper
import com.example.covide19app.Retrofit.EntityMapper.CountryMapper
import com.example.covide19app.Retrofit.RetrofitInterface
import com.example.covide19app.Utils.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AdvicesRepository constructor(
        val retrofitInterface: RetrofitInterface,
        val advicesMapper: AdvicesMapper
) {
    fun getadvicesData() : Flow<DataState<List<AdvicesModel>>> = flow{
        emit(DataState.Loading)
        try {
            val allCountryNetwork = retrofitInterface.getAdvices()
            val allCountryNetworkresults = allCountryNetwork.results
            val allcountryModels= advicesMapper.mapFromEntityList(allCountryNetworkresults)
            emit(DataState.Success(allcountryModels))
        }catch (e:Exception){
            emit(DataState.Error(e))
        }

    }
}
package com.example.covide19app.Repository

import com.example.covide19app.Model.CountriesModel
import com.example.covide19app.Model.DoctorsModel
import com.example.covide19app.Retrofit.CountryRetrofitInterface
import com.example.covide19app.Retrofit.EntityMapper.CountryMapper
import com.example.covide19app.Retrofit.EntityMapper.DoctorsMaper
import com.example.covide19app.Retrofit.RetrofitInterface
import com.example.covide19app.Utils.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DoctorRepository constructor(
        val retrofitInterface: RetrofitInterface,
        val doctorsMaper: DoctorsMaper
) {
    fun getCountryData() : Flow<DataState<List<DoctorsModel>>> = flow{
        emit(DataState.Loading)
        try {
            val allCountryNetwork = retrofitInterface.getDoctors()
            val data=allCountryNetwork.results
            val allcountryModels= doctorsMaper.mapfromEntityList(data)
            emit(DataState.Success(allcountryModels))
        }catch (e:Exception){
            emit(DataState.Error(e))
        }

    }
}
package com.example.covide19app.Repository

import com.example.covide19app.Model.CountriesModel
import com.example.covide19app.Model.PlacesModel
import com.example.covide19app.Retrofit.EntityMapper.PlacesMapper
import com.example.covide19app.Retrofit.RetrofitInterface
import com.example.covide19app.Utils.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PlacesRepository constructor(
 val retrofitInterface: RetrofitInterface,
 val placesMapper: PlacesMapper
) {
    fun getPlacesData() : Flow<DataState<List<PlacesModel>>> = flow{
        emit(DataState.Loading)
        try {
            val allPlacesNetwork = retrofitInterface.getplaces()
            val allPlacesModels= placesMapper.mapEntityListToModel(allPlacesNetwork.results)
            emit(DataState.Success(allPlacesModels))
        }catch (e:Exception){
            emit(DataState.Error(e))
        }

    }
}
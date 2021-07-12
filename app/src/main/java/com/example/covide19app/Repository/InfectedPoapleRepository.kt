package com.example.covide19app.Repository

import android.util.Log
import com.example.covide19app.Model.InfectedPoapleModel
import com.example.covide19app.Retrofit.Entities.SucssesEntity
import com.example.covide19app.Retrofit.EntityMapper.InfectedPoapleMapper
import com.example.covide19app.Retrofit.RetrofitInterface
import com.example.covide19app.Utils.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class InfectedPoapleRepository constructor(
        val retrofitInterface: RetrofitInterface,
        val infectedPoapleMapper: InfectedPoapleMapper
){
    fun postInfectedPoaple(maplist:Map<String,String>):Flow<DataState<SucssesEntity>> = flow {
        emit(DataState.Loading)
        try {
            val res=retrofitInterface.postInfected(maplist)
            Log.e("appointmentrepo", "respond " + res.objectId )
            emit(DataState.Success(res))
        }catch (e: Exception){
            Log.e("appointmentrepo", e.message.toString() )
            emit(DataState.Error(e))
        }
    }
    fun getInfectedPoaple() :Flow<DataState<List<InfectedPoapleModel>>> = flow {
        emit(DataState.Loading)
        try {
            val res=retrofitInterface.getInfectedPoaple()
            val reslist=res.results
            val infectedpoapleModels=infectedPoapleMapper.mapEntityListToModel(reslist)
            emit(DataState.Success(infectedpoapleModels))
        }catch (e:java.lang.Exception){
            emit(DataState.Error(e))
        }
    }

}
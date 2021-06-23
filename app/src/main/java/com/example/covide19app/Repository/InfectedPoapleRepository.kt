package com.example.covide19app.Repository

import android.util.Log
import com.example.covide19app.Retrofit.Entities.SucssesEntity
import com.example.covide19app.Retrofit.RetrofitInterface
import com.example.covide19app.Utils.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class InfectedPoapleRepository constructor(
        val retrofitInterface: RetrofitInterface,
){
    fun postInfectedPoaple(maplist:Map<String,String>):Flow<DataState<SucssesEntity>> = flow {
        emit(DataState.Loading)
        try {
            val res=retrofitInterface.postInfected(maplist)
            Log.e("appointmentrepo", "respond " + res.objectId )
            emit(DataState.Success(res))
        }catch (e: Exception){
            emit(DataState.Error(e))
        }
    }

}
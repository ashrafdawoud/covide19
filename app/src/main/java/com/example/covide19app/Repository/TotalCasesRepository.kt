package com.example.covide19app.Repository

import com.example.covide19app.Model.TotalCaseModel
import com.example.covide19app.Retrofit.EntityMapper.TotalCasesMapper
import com.example.covide19app.Retrofit.TotalCasesInterfaces
import com.example.covide19app.Utils.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TotalCasesRepository  constructor(
        val totalCasesInterfaces: TotalCasesInterfaces,
        val totalCasesMapper: TotalCasesMapper
) {
    fun getTotalCases() : Flow<DataState<List<TotalCaseModel>>> = flow{
        emit(DataState.Loading)
        try {
            var totalCasesNetwork=totalCasesInterfaces.getGeneralData()
            var totalcasesmodels=totalCasesMapper.mapFromEntityToList(totalCasesNetwork)
            emit(DataState.Success(totalcasesmodels))
        }catch (e:Exception){
            emit(DataState.Error(e))
        }
    }

}
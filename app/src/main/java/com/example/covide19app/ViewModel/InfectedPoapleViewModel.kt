package com.example.covide19app.ViewModel

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.covide19app.Repository.InfectedPoapleRepository
import com.example.covide19app.Retrofit.Entities.SucssesEntity
import com.example.covide19app.Utils.DataState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class InfectedPoapleViewModel @ViewModelInject constructor(
        private val infectedPoapleRepository: InfectedPoapleRepository,
        @Assisted private val savedStateHandle: SavedStateHandle
):ViewModel() {
    val _datastate:MutableLiveData<DataState<SucssesEntity>> = MutableLiveData()
    val datasate:LiveData<DataState<SucssesEntity>>
    get() = _datastate

    fun postInfected(maplist:Map<String,String>){
        viewModelScope.launch {
            infectedPoapleRepository.postInfectedPoaple(maplist).onEach { _datastate.value=it }.launchIn(viewModelScope)
        }
    }
}
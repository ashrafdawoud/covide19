package com.example.covide19app.ViewModel

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.covide19app.Model.AdvicesModel
import com.example.covide19app.Model.CountriesModel
import com.example.covide19app.Repository.AdvicesRepository
import com.example.covide19app.Repository.CountriesRepository
import com.example.covide19app.Utils.DataState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi

class AdvicesViewModel @ViewModelInject constructor(
        private val advicesRepository: AdvicesRepository,
        @Assisted private val savedStateHandle: SavedStateHandle
): ViewModel() {
    val _dataset: MutableLiveData<DataState<List<AdvicesModel>>> = MutableLiveData()
    val dataset: LiveData<DataState<List<AdvicesModel>>>
        get() = _dataset

    fun getAllCountries(){
        viewModelScope.launch {
            advicesRepository.getadvicesData().onEach { _dataset.value = it }.launchIn(viewModelScope)
        }
    }
}
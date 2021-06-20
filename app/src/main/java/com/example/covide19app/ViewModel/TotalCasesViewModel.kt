package com.example.covide19app.ViewModel

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.covide19app.Model.TotalCaseModel
import com.example.covide19app.Repository.TotalCasesRepository
import com.example.covide19app.Utils.DataState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class TotalCasesViewModel @ViewModelInject constructor(
        private val totalCasesRepository: TotalCasesRepository,
        @Assisted private val savedStateHandle: SavedStateHandle)
    : ViewModel() {
    val _dataset: MutableLiveData<DataState<List<TotalCaseModel>>> = MutableLiveData()
    val dataset: LiveData<DataState<List<TotalCaseModel>>>
        get() = _dataset

    fun getTotalCases() {
        viewModelScope.launch {
            totalCasesRepository.getTotalCases().onEach { _dataset.value = it }.launchIn(viewModelScope)
        }
    }
}
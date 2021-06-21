package com.example.covide19app.ViewModel

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.covide19app.Model.PlacesModel
import com.example.covide19app.Repository.PlacesRepository
import com.example.covide19app.Utils.DataState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class PlacesViewModel @ViewModelInject constructor(
        private val placesRepository: PlacesRepository,
        @Assisted private val savedStateHandle: SavedStateHandle
) :ViewModel() {
    val _dataset:MutableLiveData<DataState<List<PlacesModel>>> = MutableLiveData()
    val dataset:LiveData<DataState<List<PlacesModel>>>
    get() = _dataset

    fun getPlacesData(){
        viewModelScope.launch {
            placesRepository.getPlacesData().onEach { _dataset.value=it }.launchIn(viewModelScope)
        }
    }
}
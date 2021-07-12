package com.example.covide19app.ViewModel

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.covide19app.Model.CountriesModel
import com.example.covide19app.Model.DoctorsModel
import com.example.covide19app.Repository.CountriesRepository
import com.example.covide19app.Repository.DoctorRepository
import com.example.covide19app.Utils.DataState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class DoctorsViewModel @ViewModelInject constructor(
        private val doctorRepository: DoctorRepository,
        @Assisted private val savedStateHandle: SavedStateHandle
): ViewModel() {
    val _dataset: MutableLiveData<DataState<List<DoctorsModel>>> = MutableLiveData()
    val dataset: LiveData<DataState<List<DoctorsModel>>>
        get() = _dataset

    fun getAllCountries(){
        viewModelScope.launch {
            doctorRepository.getCountryData().onEach { _dataset.value=it }.launchIn(viewModelScope)
        }
    }
}
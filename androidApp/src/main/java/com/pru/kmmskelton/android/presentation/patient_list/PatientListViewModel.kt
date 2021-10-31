package com.pru.kmmskelton.android.presentation.patient_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pru.kmmskelton.data.models.response.Patient
import com.pru.kmmskelton.domain.usecases.PatientUseCase
import com.pru.kmmskelton.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@InternalCoroutinesApi
@HiltViewModel
class PatientListViewModel @Inject constructor(
    private val patientUseCase: PatientUseCase,
) : ViewModel() {

    private val _patientsState = MutableLiveData<Resource<List<Patient>>>()
    val patientsState: LiveData<Resource<List<Patient>>> get() = _patientsState

    init {
        getPatients()
    }

    fun getPatients() {
        viewModelScope.launch(Dispatchers.IO) {
            patientUseCase.executePatientList().collectLatest { result ->
                when (result) {
                    is Resource.Loading -> {
                        _patientsState.postValue(Resource.Loading())
                    }
                    is Resource.Success -> {
                        _patientsState.postValue(Resource.Success(result.data!!))
                    }
                    is Resource.Error -> {
                        _patientsState.postValue(Resource.Error(result.errorMessage))
                    }
                }
            }
        }
    }
}

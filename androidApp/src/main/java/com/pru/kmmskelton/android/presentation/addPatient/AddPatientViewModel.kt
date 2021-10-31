package com.pru.kmmskelton.android.presentation.addPatient

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pru.kmmskelton.domain.usecases.PatientUseCase
import com.pru.kmmskelton.util.Resource
import com.pru.kmmskelton.util.ValidateForm
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddPatientViewModel @Inject constructor(
    private val useCase: PatientUseCase,
) : ViewModel() {

    private val _patientsState = MutableSharedFlow<Resource<Boolean>>()
    val patientsState: SharedFlow<Resource<Boolean>> get() = _patientsState.asSharedFlow()

    fun validatePatientForm(
        patientName: String?,
        patientAge: String?,
        patientILLCode: String?,
    ): ValidateForm = useCase.validatePatientForm(patientName = patientName,
        patientAge = patientAge,
        patientILLCode = patientILLCode)

    fun admitPatient(
        patientName: String?,
        patientAge: String?,
        patientILLCode: String?,
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            useCase.executeAdmitPatient(patientName = patientName,
                patientAge = patientAge,
                patientILLCode = patientILLCode).collectLatest { result ->
                when (result) {
                    is Resource.Success -> {
                        _patientsState.emit(Resource.Success(true))
                    }
                    is Resource.Error -> {
                        _patientsState.emit(Resource.Error(result.errorMessage))
                    }
                    else -> {

                    }
                }
            }
        }
    }
}
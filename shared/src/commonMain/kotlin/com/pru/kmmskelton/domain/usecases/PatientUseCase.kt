package com.pru.kmmskelton.domain.usecases

import com.pru.kmmskelton.data.models.response.Patient
import com.pru.kmmskelton.domain.repository.Repository
import com.pru.kmmskelton.util.Resource
import com.pru.kmmskelton.util.ValidateForm
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PatientUseCase(private val repository: Repository) {

    suspend fun executePatientList(): Flow<Resource<List<Patient>>> =
        flow<Resource<List<Patient>>> {
            emit(Resource.Loading())
            kotlin.runCatching {
                repository.getAllPatients()
            }.onSuccess {
                if (it.success == true) {
                    emit(Resource.Success(it.returnValue ?: emptyList()))
                } else {
                    emit(Resource.Error(it.message))
                }
            }.onFailure {
                emit(Resource.Error(it.message))
            }
        }

    fun validatePatientForm(
        patientName: String?,
        patientAge: String?,
        patientILLCode: String?,
    ): ValidateForm {
        return when {
            patientName?.isBlank() == true -> ValidateForm.ISValidFalse(message = "Patient Name Cannot be empty")
            patientAge?.isBlank() == true -> ValidateForm.ISValidFalse(message = "Patient Age Cannot be empty")
            patientILLCode?.isBlank() == true -> ValidateForm.ISValidFalse(message = "Patient ILL Code Cannot be empty")
            else -> ValidateForm.ISValidTrue
        }
    }

    fun executeAdmitPatient(
        patientName: String?,
        patientAge: String?,
        patientILLCode: String?,
    ): Flow<Resource<Boolean>> =
        flow<Resource<Boolean>> {
            emit(Resource.Loading())
            kotlin.runCatching {
                val patient = Patient(patientAge = patientAge?.toInt(),
                    patientIllCode = patientILLCode,
                    patientName = patientName)
                repository.addPatient(patient = patient)
            }.onSuccess {
                if (it.success == true && it.returnValue ?: 0 > 0) {
                    emit(Resource.Success(true))
                } else {
                    emit(Resource.Error(it.message))
                }
            }.onFailure {
                emit(Resource.Error(it.message))
            }
        }
}
package com.pru.kmmskelton.data.repository

import com.pru.kmmskelton.data.models.response.BaseResponse
import com.pru.kmmskelton.data.models.response.Patient
import com.pru.kmmskelton.data.remote.APIService
import com.pru.kmmskelton.domain.repository.Repository

class RepositorySdk(private val apiService: APIService) : Repository {

//    private var apiService: APIService = APIService()

    @Throws(Exception::class)
    override suspend fun getAllPatients(): BaseResponse<List<Patient>> = apiService.getAllPatients()

    @Throws(Exception::class)
    override suspend fun addPatient(patient: Patient): BaseResponse<Int> =
        apiService.addPatient(patient)
}
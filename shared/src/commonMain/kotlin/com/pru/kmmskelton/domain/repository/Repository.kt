package com.pru.kmmskelton.domain.repository

import com.pru.kmmskelton.data.models.response.BaseResponse
import com.pru.kmmskelton.data.models.response.Patient
import com.pru.kmmskelton.util.Resource
import kotlinx.coroutines.flow.Flow

interface Repository {
    @Throws(Exception::class)
    suspend fun getAllPatients(): BaseResponse<List<Patient>>

    @Throws(Exception::class)
    suspend fun addPatient(patient: Patient): BaseResponse<Int>
}
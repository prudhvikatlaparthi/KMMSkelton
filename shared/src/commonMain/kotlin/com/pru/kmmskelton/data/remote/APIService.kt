package com.pru.kmmskelton.data.remote

import com.pru.kmmskelton.constants.Constants
import com.pru.kmmskelton.data.models.response.BaseResponse
import com.pru.kmmskelton.data.models.response.Patient
import com.pru.kmmskelton.httpClient
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import io.ktor.http.*

class APIService {

    private val httpClient = httpClient {
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.ALL
        }
        install(JsonFeature) {
            val json = kotlinx.serialization.json.Json {
                ignoreUnknownKeys = true
                isLenient = true
            }
            serializer = KotlinxSerializer()
        }
    }

    @Throws(Exception::class)
    suspend fun getAllPatients(): BaseResponse<List<Patient>> {
        return httpClient.get(url = prepareUrl(Constants.kPatients))
    }

    @Throws(Exception::class)
    suspend fun addPatient(patient: Patient): BaseResponse<Int> {
        return httpClient.post(url = prepareUrl(Constants.kAddPatient)) {
            body = patient
            headers {
                append("Content-Type", "application/json")
            }
        }
    }

    private fun prepareUrl(endPoint: String) = URLBuilder(Constants.kBaseUrl + endPoint).build()

}
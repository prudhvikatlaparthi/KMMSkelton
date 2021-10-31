package com.pru.kmmskelton.data.models.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Patient(
    @SerialName("patient_age")
    var patientAge: Int?,
    @SerialName("patient_id")
    var patientId: Int? = null,
    @SerialName("patient_illcode")
    var patientIllCode: String?,
    @SerialName("patient_name")
    var patientName: String?
)
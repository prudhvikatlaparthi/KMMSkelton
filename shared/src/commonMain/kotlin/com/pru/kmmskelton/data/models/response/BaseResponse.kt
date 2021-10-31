package com.pru.kmmskelton.data.models.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BaseResponse<T>(
    @SerialName("message")
    var message: String?,
    @SerialName("returnValue")
    var returnValue: T?,
    @SerialName("success")
    var success: Boolean?
)
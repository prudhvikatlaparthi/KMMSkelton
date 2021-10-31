package com.pru.kmmskelton.util

sealed class ValidateForm(var message: String?) {
    object ISValidTrue : ValidateForm(message = null)
    class ISValidFalse(message: String) : ValidateForm(message = message)
}
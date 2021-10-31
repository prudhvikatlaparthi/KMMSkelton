package com.pru.kmmskelton.android.utils

import android.widget.Toast
import androidx.fragment.app.Fragment

fun Fragment.showMessage(message: String) {
    val toast = Toast.makeText(this.requireContext(), message, Toast.LENGTH_LONG)
    toast.show()
}
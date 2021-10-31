package com.pru.kmmskelton.preferences

import com.russhwolf.settings.Settings
import com.russhwolf.settings.get
import com.russhwolf.settings.set

object AppPreferences {
    private val settings: Settings = Settings()

    // Keys
    private const val kName = "KName"



    fun saveName(name: String?) {
        settings[kName] = name
    }

    fun getName(): String? = settings[kName]
}
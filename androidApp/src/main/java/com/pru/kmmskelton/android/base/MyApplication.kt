package com.pru.kmmskelton.android.base

import android.app.Application
import com.pru.kmmskelton.koin.Koin
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : Application(){

    override fun onCreate() {
        super.onCreate()
    }
}
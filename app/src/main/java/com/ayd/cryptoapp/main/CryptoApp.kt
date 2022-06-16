package com.ayd.cryptoapp.main

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class CryptoApp: Application() {
    override fun onCreate() {
        super.onCreate()
    }

}
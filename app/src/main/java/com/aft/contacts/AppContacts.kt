package com.aft.contacts

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AppContacts : Application() {
    override fun onCreate() {
        super.onCreate()
    }

}
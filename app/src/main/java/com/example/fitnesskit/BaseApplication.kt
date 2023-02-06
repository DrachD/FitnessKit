package com.example.fitnesskit

import android.app.Application
import com.example.fitnesskit.di.AppComponent
import com.example.fitnesskit.di.DaggerAppComponent

class BaseApplication : Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(applicationContext)
    }
}
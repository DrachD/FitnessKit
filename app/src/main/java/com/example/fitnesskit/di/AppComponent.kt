package com.example.fitnesskit.di

import android.content.Context
import com.example.fitnesskit.data.di.RetrofitFitnessKitRepositoryModule
import com.example.fitnesskit.data.di.RetrofitFitnessKitSourceModule
import com.example.fitnesskit.data.di.RetrofitProvideModule
import com.example.fitnesskit.presenter.tabs.lessons.LessonsFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    RetrofitProvideModule::class,
    RetrofitFitnessKitSourceModule::class,
    RetrofitFitnessKitRepositoryModule::class
])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun inject(fragment: LessonsFragment)
}
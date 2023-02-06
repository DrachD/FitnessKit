package com.example.fitnesskit.data.di

import com.example.fitnesskit.data.datasources.RetrofitFitnessKitSource
import com.example.fitnesskit.data.datasources.RetrofitFitnessKitSourceImpl
import dagger.Binds
import dagger.Module

@Module
abstract class RetrofitFitnessKitSourceModule {

    @Binds
    abstract fun provideFitnessKitRepository(fitnessKitSource: RetrofitFitnessKitSourceImpl): RetrofitFitnessKitSource
}
package com.example.fitnesskit.data.di

import com.example.fitnesskit.data.RetrofitFitnessKitRepositoryImpl
import com.example.fitnesskit.domain.repository.RetrofitFitnessKitRepository
import dagger.Binds
import dagger.Module

@Module
abstract class RetrofitFitnessKitRepositoryModule {

    @Binds
    abstract fun provideFitnessKitRepository(fitnessKitRepository: RetrofitFitnessKitRepositoryImpl): RetrofitFitnessKitRepository
}
package com.example.fitnesskit.data.di

import com.example.fitnesskit.data.Constants
import com.example.fitnesskit.data.FitnessKitApi
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
class RetrofitProvideModule {

    @Singleton
    @Provides
    fun createRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideFitnessKitApi(retrofit: Retrofit): FitnessKitApi {
        return retrofit.create(FitnessKitApi::class.java)
    }
}
package com.example.fitnesskit.data

import com.example.fitnesskit.common.FitnessClub
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface FitnessKitApi {

    @GET("schedule/get_v3")
    suspend fun fetchFitnessClub(@Query("club_id") id: Int): Response<FitnessClub>
}
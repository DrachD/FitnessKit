package com.example.fitnesskit.data

import com.example.fitnesskit.common.FitnessClub
import retrofit2.Response
import retrofit2.http.GET

interface FitnessKitApi {

    @GET("https://olimpia.fitnesskit-admin.ru/schedule/get_v3/?club_id=2")
    suspend fun fetchFitnessClub(): Response<FitnessClub>
}
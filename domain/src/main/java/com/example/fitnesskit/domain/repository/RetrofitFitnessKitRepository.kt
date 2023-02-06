package com.example.fitnesskit.domain.repository

import com.example.fitnesskit.common.FitnessClub
import com.example.fitnesskit.domain.GeneralResponse
import retrofit2.Response

interface RetrofitFitnessKitRepository {

    suspend fun fetchFitnessClub(id: Int): GeneralResponse<FitnessClub>
}
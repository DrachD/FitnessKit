package com.example.fitnesskit.data.datasources

import com.example.fitnesskit.common.FitnessClub
import com.example.fitnesskit.domain.GeneralResponse

interface RetrofitFitnessKitSource {
    suspend fun fetchFitnessClub(id: Int): GeneralResponse<FitnessClub>
}
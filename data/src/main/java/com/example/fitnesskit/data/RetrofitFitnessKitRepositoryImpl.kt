package com.example.fitnesskit.data

import com.example.fitnesskit.common.FitnessClub
import com.example.fitnesskit.data.datasources.RetrofitFitnessKitSource
import com.example.fitnesskit.domain.GeneralResponse
import com.example.fitnesskit.domain.repository.RetrofitFitnessKitRepository
import javax.inject.Inject

class RetrofitFitnessKitRepositoryImpl @Inject constructor(
    private val retrofitFitnessKitSource: RetrofitFitnessKitSource
) : RetrofitFitnessKitRepository {
    override suspend fun fetchFitnessClub(id: Int): GeneralResponse<FitnessClub> {
        return retrofitFitnessKitSource.fetchFitnessClub(id)
    }
}
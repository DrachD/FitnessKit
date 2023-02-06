package com.example.fitnesskit.data.datasources

import com.example.fitnesskit.data.FitnessKitApi
import com.example.fitnesskit.data.datasources.base.BaseRetrofitSource
import javax.inject.Inject

class RetrofitFitnessKitSourceImpl @Inject constructor(
    private val fitnessKitApi: FitnessKitApi
) : BaseRetrofitSource(), RetrofitFitnessKitSource {

    override suspend fun fetchFitnessClub(id: Int) = wrapRetrofitException {
        fitnessKitApi.fetchFitnessClub()
    }
}
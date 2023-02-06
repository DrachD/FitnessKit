package com.example.fitnesskit.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.fitnesskit.domain.repository.RetrofitFitnessKitRepository
import com.example.fitnesskit.presenter.tabs.lessons.LessonsViewModel
import javax.inject.Inject

class LessonsViewModelFactory @Inject constructor(
    private val repository: RetrofitFitnessKitRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LessonsViewModel(repository) as T
    }
}
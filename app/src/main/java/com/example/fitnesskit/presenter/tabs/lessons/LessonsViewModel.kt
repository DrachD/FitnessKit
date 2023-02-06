package com.example.fitnesskit.presenter.tabs.lessons

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitnesskit.common.FitnessClub
import com.example.fitnesskit.domain.GeneralResponse
import com.example.fitnesskit.domain.repository.RetrofitFitnessKitRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class LessonsViewModel @Inject constructor(
    private val retrofitFitnessKitRepository: RetrofitFitnessKitRepository
) : ViewModel() {

    private val _fetchFitnessClubEvent = MutableLiveData<FitnessClub>()
    val fetchFitnessClubEvent: MutableLiveData<FitnessClub> = _fetchFitnessClubEvent

    private val _showMessageEvent = MutableLiveData<String>()
    val showMessageEvent: MutableLiveData<String> = _showMessageEvent

    fun fetchFitnessClub(id: Int) = viewModelScope.launch {

        when(val response = retrofitFitnessKitRepository.fetchFitnessClub(id)) {
            is GeneralResponse.Success -> {
                _fetchFitnessClubEvent.value = response.data!!
            }
            is GeneralResponse.Error -> {
                showMessage(response.errorMessage!!)
            }
        }
    }

    private fun showMessage(message: String) {
        _showMessageEvent.value = message
    }
}
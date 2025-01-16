package com.iagoaf.plannerjetpack.src.home.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iagoaf.plannerjetpack.src.home.domain.entities.ActivityEntity
import com.iagoaf.plannerjetpack.src.home.domain.repository.IActivityRepository
import com.iagoaf.plannerjetpack.src.home.presentation.HeadFormSaveActivityListener
import com.iagoaf.plannerjetpack.src.home.presentation.HeadFormSaveActivityState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class HeadFormSaveActivityViewModel(
    val activityRepository: IActivityRepository
) : ViewModel() {

    private val _state = MutableStateFlow<HeadFormSaveActivityState>(HeadFormSaveActivityState.Idle)
    val state = _state

    private val _listener = MutableStateFlow<HeadFormSaveActivityListener>(HeadFormSaveActivityListener.idle)
    val listener = _listener

    fun saveActivity(
        name: String,
        date: String,
        time: String
    ) {
        _state.value = HeadFormSaveActivityState.Loading
        viewModelScope.launch {
            activityRepository.saveActivity(
                ActivityEntity(
                    name = name,
                    date = date,
                    time = time
                )
            )
            _state.value = HeadFormSaveActivityState.Idle
            _listener.value = HeadFormSaveActivityListener.savedActivity

        }
    }

}

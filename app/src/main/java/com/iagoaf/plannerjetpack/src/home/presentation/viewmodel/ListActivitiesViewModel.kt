package com.iagoaf.plannerjetpack.src.home.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iagoaf.plannerjetpack.src.home.domain.models.ActivityModel
import com.iagoaf.plannerjetpack.src.home.domain.repository.IActivityRepository
import com.iagoaf.plannerjetpack.src.home.presentation.states.ListActivitiesState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import androidx.lifecycle.ViewModelProvider

class ListActivitiesViewModel(
    val activityRepository: IActivityRepository
) : ViewModel() {
    private val _state = MutableStateFlow<ListActivitiesState>(ListActivitiesState.Idle)
    val state = _state

    private val list = mutableListOf<ActivityModel>()
    private val currentDate = Date()
    private val dateTimeFormat = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())

    fun getActivities() {
        _state.value = ListActivitiesState.Loading // Emitindo estado de carregamento
        viewModelScope.launch {
            try {
                activityRepository.getAll()
                    .collect { activities ->
                        val updatedList = activities.map { value ->
                            val activityDateTime =
                                dateTimeFormat.parse("${value.date} ${value.time}")
                            val isDone = activityDateTime.before(currentDate)

                            ActivityModel(
                                id = value.id,
                                name = value.name,
                                date = value.date,
                                time = value.time,
                                isDone = isDone
                            )
                        }.sortedBy {
                            dateTimeFormat.parse("${it.date} ${it.time}")
                        }

                        _state.value = ListActivitiesState.Success(updatedList)
                    }
            } catch (e: Exception) {
                _state.value = ListActivitiesState.Error(e.message ?: "Erro ao carregar atividades")
            }
        }
    }

}

class ListActivitiesViewModelFactory(
    private val activityRepository: IActivityRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ListActivitiesViewModel::class.java)) {
            return ListActivitiesViewModel(activityRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

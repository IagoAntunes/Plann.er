package com.iagoaf.plannerjetpack.src.home.presentation.states

import com.iagoaf.plannerjetpack.src.home.domain.models.ActivityModel

sealed class ListActivitiesState {
    object Idle : ListActivitiesState()
    object Loading : ListActivitiesState()
    data class Success(val listActivities: List<ActivityModel>) : ListActivitiesState()
    data class Error(val message: String) : ListActivitiesState()

}
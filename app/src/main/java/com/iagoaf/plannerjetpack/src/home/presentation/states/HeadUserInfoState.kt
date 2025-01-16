package com.iagoaf.plannerjetpack.src.home.presentation.states

import com.iagoaf.plannerjetpack.src.registerUser.domain.models.UserModel

sealed class HeadUserInfoState {
    object Idle : HeadUserInfoState()
    object Loading : HeadUserInfoState()
    data class Success(val user: UserModel) : HeadUserInfoState()
    data class Error(val message: String) : HeadUserInfoState()
}
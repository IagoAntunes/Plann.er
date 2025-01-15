package com.iagoaf.plannerjetpack.src.registerUser.presentation

sealed class RegisterUserState {
}

sealed class RegisterUserListener {
    object IdleUser : RegisterUserListener()
    object RegisterUserSuccess : RegisterUserListener()
    object FailureUserSuccess : RegisterUserListener()
}
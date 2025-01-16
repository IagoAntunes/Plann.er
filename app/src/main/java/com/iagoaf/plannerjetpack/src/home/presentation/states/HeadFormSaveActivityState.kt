package com.iagoaf.plannerjetpack.src.home.presentation.states

sealed class HeadFormSaveActivityState {
    object Idle : HeadFormSaveActivityState()
    object Loading : HeadFormSaveActivityState()
}

sealed class HeadFormSaveActivityListener{
    object idle : HeadFormSaveActivityListener()
    object savedActivity : HeadFormSaveActivityListener()
    object errorSavedActivity : HeadFormSaveActivityListener()
}
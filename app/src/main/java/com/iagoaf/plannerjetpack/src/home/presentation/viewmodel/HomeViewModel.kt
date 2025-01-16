package com.iagoaf.plannerjetpack.src.home.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.iagoaf.plannerjetpack.src.registerUser.domain.repository.IUserRepository

class HomeViewModel(
    val userRepository: IUserRepository
) : ViewModel() {


    fun loadUserInfo(){

    }


}
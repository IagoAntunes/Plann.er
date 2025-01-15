package com.iagoaf.plannerjetpack.src.registerUser.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iagoaf.plannerjetpack.src.registerUser.domain.entities.UserEntity
import com.iagoaf.plannerjetpack.src.registerUser.domain.models.UserModel
import com.iagoaf.plannerjetpack.src.registerUser.domain.repository.IUserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class RegisterUserViewModel(
    val userRepository: IUserRepository
) : ViewModel() {

    private val _state =
        MutableStateFlow<RegisterUserListener>(RegisterUserListener.IdleUser)
    val state = _state


    var user: UserModel? = null

    fun saveUser(
        imagePath: String,
        name: String,
        email: String,
        phone: String,
    ) {
        user = UserModel(
            imagePath = imagePath,
            name = name,
            email = email,
            phone = phone,
        )
        viewModelScope.launch {
            userRepository.insertUser(
                UserEntity(
                    imagePath = imagePath,
                    name = name,
                    email = email,
                    phone = phone,
                )
            )
            _state.value = RegisterUserListener.RegisterUserSuccess

        }

    }

}
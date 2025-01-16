package com.iagoaf.plannerjetpack.src.home.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.iagoaf.plannerjetpack.src.home.presentation.states.HeadUserInfoState
import com.iagoaf.plannerjetpack.src.registerUser.domain.models.UserModel
import com.iagoaf.plannerjetpack.src.registerUser.domain.repository.IUserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class HeadUserInfoViewModel(
    val userRepository: IUserRepository
) : ViewModel() {

    private val _state = MutableStateFlow<HeadUserInfoState>(HeadUserInfoState.Loading)
    val state = _state


    fun loadUserData() {
        viewModelScope.launch {
            val listUser = userRepository.getUser()
            val userEntity = listUser.last()
            _state.value = HeadUserInfoState.Success(
                UserModel(
                    userEntity.imagePath,
                    userEntity.name,
                    userEntity.email,
                    userEntity.phone,
                )
            )
        }
    }

}


class HeadUserInfoViewModelFactory(
    private val userRepository: IUserRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HeadUserInfoViewModel::class.java)) {
            return HeadUserInfoViewModel(userRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

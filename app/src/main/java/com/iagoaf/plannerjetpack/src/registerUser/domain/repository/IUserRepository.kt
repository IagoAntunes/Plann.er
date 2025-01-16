package com.iagoaf.plannerjetpack.src.registerUser.domain.repository

import com.iagoaf.plannerjetpack.src.registerUser.domain.entities.UserEntity

interface IUserRepository {

    suspend fun insertUser(user: UserEntity): Unit

    suspend fun getUser(): List<UserEntity>

}
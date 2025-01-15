package com.iagoaf.plannerjetpack.src.registerUser.infra.repository

import com.iagoaf.plannerjetpack.src.registerUser.domain.entities.UserEntity
import com.iagoaf.plannerjetpack.src.registerUser.domain.repository.IUserRepository
import com.iagoaf.plannerjetpack.src.registerUser.external.dao.UserDao

class UserRepositoryImpl(
    private val userDao: UserDao
) : IUserRepository {
    override suspend fun insertUser(user: UserEntity) {
        userDao.insert(user)
    }

    override suspend fun getUser(id: Int): UserEntity {
        return userDao.getUserById(id)
    }
}
package com.iagoaf.plannerjetpack.src.home.domain.repository

import com.iagoaf.plannerjetpack.src.home.domain.entities.ActivityEntity
import kotlinx.coroutines.flow.Flow

interface IActivityRepository {

    suspend fun saveActivity(activity: ActivityEntity)
    fun getAll(): Flow<ActivityEntity>

}
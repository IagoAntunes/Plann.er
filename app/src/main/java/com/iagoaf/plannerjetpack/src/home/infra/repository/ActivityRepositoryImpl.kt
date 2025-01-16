package com.iagoaf.plannerjetpack.src.home.infra.repository

import com.iagoaf.plannerjetpack.src.home.domain.entities.ActivityEntity
import com.iagoaf.plannerjetpack.src.home.domain.repository.IActivityRepository
import com.iagoaf.plannerjetpack.src.home.external.dao.ActivityDao
import kotlinx.coroutines.flow.Flow

class ActivityRepositoryImpl(
    val activityDao: ActivityDao
) : IActivityRepository {
    override suspend fun saveActivity(activity: ActivityEntity) {
        return activityDao.saveActivity(activity)
    }

    override fun getAll(): Flow<ActivityEntity> {
        return activityDao.getAllActivities()
    }
}
package com.iagoaf.plannerjetpack.src.home.external.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.iagoaf.plannerjetpack.src.home.domain.entities.ActivityEntity
import kotlinx.coroutines.flow.Flow

@Dao()
interface ActivityDao {
    @Insert
    suspend fun saveActivity(activity: ActivityEntity)

    @Query("SELECT * FROM activity")
    fun getAllActivities(): Flow<ActivityEntity>

}
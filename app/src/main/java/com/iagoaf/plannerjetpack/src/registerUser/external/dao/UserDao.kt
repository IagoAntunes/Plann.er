package com.iagoaf.plannerjetpack.src.registerUser.external.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.iagoaf.plannerjetpack.src.registerUser.domain.entities.UserEntity

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.Companion.IGNORE)
    suspend fun insert(user: UserEntity)

    @Delete()
    suspend fun delete(user: UserEntity)

    @Query("SELECT * from users WHERE id = :id")
    suspend fun getUserById(id: Int): UserEntity

}
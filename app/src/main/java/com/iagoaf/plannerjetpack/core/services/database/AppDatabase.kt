package com.iagoaf.plannerjetpack.core.services.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.iagoaf.plannerjetpack.src.registerUser.domain.entities.UserEntity
import com.iagoaf.plannerjetpack.src.registerUser.external.dao.UserDao

@Database(
    entities = [
        UserEntity::class
    ], version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var Instance: AppDatabase? = null


        fun getDatabase(context: Context): AppDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, AppDatabase::class.java, "app_database")
                    .build()
                    .also { Instance = it }
            }
        }
    }


}
package com.iagoaf.plannerjetpack.core.services.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.iagoaf.plannerjetpack.src.home.domain.entities.ActivityEntity
import com.iagoaf.plannerjetpack.src.home.external.dao.ActivityDao
import com.iagoaf.plannerjetpack.src.registerUser.domain.entities.UserEntity
import com.iagoaf.plannerjetpack.src.registerUser.external.dao.UserDao

@Database(
    entities = [
        UserEntity::class,
        ActivityEntity::class
    ], version = 2
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun activityDao(): ActivityDao

    companion object {
        @Volatile
        private var Instance: AppDatabase? = null


        fun getDatabase(context: Context): AppDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, AppDatabase::class.java, "app_database")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }


}


package com.iagoaf.plannerjetpack.src.home.domain.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "activity")
data class ActivityEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val date: String,
    val time: String
)
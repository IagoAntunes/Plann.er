package com.iagoaf.plannerjetpack.src.registerUser.domain.entities
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val imagePath: String,
    val name: String,
    val email: String,
    val phone: String,
)
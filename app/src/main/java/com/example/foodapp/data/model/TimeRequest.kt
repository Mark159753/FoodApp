package com.example.foodapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "time_request")
data class TimeRequest (
    @PrimaryKey(autoGenerate = false)
    var _id:Int = 1,
    val lastTimeCategory:Long?,
    val lastTimeRandomMeal:Long?
)
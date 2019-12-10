package com.example.foodapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "time_random_meal_request")
class TimeRandomMealRequest(val lastTimeRandomMealRequest:Long) {
    @PrimaryKey(autoGenerate = false)
    var _id:Int = 1
}
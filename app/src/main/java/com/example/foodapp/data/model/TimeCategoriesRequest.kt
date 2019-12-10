package com.example.foodapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "time_categories_request")
data class TimeCategoriesRequest (
    @PrimaryKey(autoGenerate = false)
    var _id:Int = 1,
    val lastTimeCategory:Long?
)
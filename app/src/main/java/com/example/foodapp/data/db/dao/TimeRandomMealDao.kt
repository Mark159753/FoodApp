package com.example.foodapp.data.db.dao

import androidx.room.*
import com.example.foodapp.data.model.TimeCategoriesRequest
import com.example.foodapp.data.model.TimeRandomMealRequest

@Dao
interface TimeRandomMealDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(timeRequest: TimeRandomMealRequest)

    @Query("SELECT * FROM time_random_meal_request")
    suspend fun getAllTimesRequests(): TimeRandomMealRequest


}
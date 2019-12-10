package com.example.foodapp.data.db.dao

import androidx.room.*
import com.example.foodapp.data.model.TimeCategoriesRequest

@Dao
interface CategoriesTimeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(timeRequest: TimeCategoriesRequest)

    @Query("SELECT * FROM time_categories_request")
    suspend fun getAllTimesRequests():TimeCategoriesRequest

}
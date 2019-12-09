package com.example.foodapp.data.db.dao

import androidx.room.*
import com.example.foodapp.data.model.TimeRequest

@Dao
interface TimeRequestDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(timeRequest: TimeRequest)

    @Query("SELECT * FROM time_request")
    suspend fun getAllTimesRequests():TimeRequest

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateTime(time:TimeRequest)
}
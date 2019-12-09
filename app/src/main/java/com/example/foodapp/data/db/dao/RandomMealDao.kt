package com.example.foodapp.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.foodapp.data.model.Category
import com.example.foodapp.data.model.Meal

@Dao
interface RandomMealDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(list: List<Meal>)

    @Query("SELECT * FROM random_meals")
    fun getRandomMeal(): LiveData<List<Meal>>

    @Query("DELETE FROM random_meals")
    suspend fun deleteAllRandomMeal()
}
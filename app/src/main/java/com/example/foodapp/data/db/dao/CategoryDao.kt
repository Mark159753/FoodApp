package com.example.foodapp.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.foodapp.data.model.Category

@Dao
interface CategoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(list: List<Category>)

    @Query("SELECT * FROM category_table")
    fun getCategories():LiveData<List<Category>>

    @Query("DELETE FROM category_table")
    suspend fun deleteAllCategories()
}
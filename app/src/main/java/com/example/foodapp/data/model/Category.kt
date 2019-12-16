package com.example.foodapp.data.model


import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "Category_table")
data class Category(
    val idCategory: String,
    val strCategory: String,
    val strCategoryDescription: String,
    val strCategoryThumb: String
):Parcelable{
    @PrimaryKey(autoGenerate = true)
    var _id:Int? = null
}
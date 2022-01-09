package com.daewon.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "card")
data class CardEntity(
    @field:SerializedName("user_id")
    val userId: Int,
    @field:SerializedName("img_url")
    val imgUrl: String,
    @field:SerializedName("description")
    val description: String,
    @PrimaryKey(autoGenerate = false)
    @field:SerializedName("id")
    val id: Int
)

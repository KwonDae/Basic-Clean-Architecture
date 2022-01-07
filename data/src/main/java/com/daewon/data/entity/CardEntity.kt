package com.daewon.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "card")
data class CardEntity(
    @SerializedName("user_id")
    val userId: Int,
    @SerializedName("img_url")
    val imgUrl: String,
    @SerializedName("description")
    val description: String,
    @PrimaryKey(autoGenerate = false)
    @SerializedName("id")
    val id: Int
)

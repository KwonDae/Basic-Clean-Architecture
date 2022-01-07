package com.daewon.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "user")
data class UserEntity(
    @SerializedName("nickname")
    val nickname: String?,
    @SerializedName("introduction")
    val introduce: String?,
    @PrimaryKey(autoGenerate = false)
    @SerializedName("id")
    val id: Int
)

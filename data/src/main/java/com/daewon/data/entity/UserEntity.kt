package com.daewon.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "user")
data class UserEntity(
    @field:SerializedName("nickname")
    val nickname: String?,
    @field:SerializedName("introduction")
    val introduce: String?,
    @PrimaryKey(autoGenerate = false)
    @field:SerializedName("id")
    val id: Int
)

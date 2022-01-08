package com.daewon.data.response

import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class CardDetailResponse(
    @SerializedName("nickname")
    val nickname: String?,
    @SerializedName("introduction")
    val introduce: String?,
    @PrimaryKey(autoGenerate = false)
    @SerializedName("id")
    val id: Int
)
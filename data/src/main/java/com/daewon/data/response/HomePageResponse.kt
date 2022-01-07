package com.daewon.data.response

import com.daewon.data.entity.CardEntity
import com.daewon.data.entity.UserEntity
import com.google.gson.annotations.SerializedName

data class HomePageResponse(
    @SerializedName("ok")
    val status: Boolean,
    @SerializedName("popular_users")
    val users: List<UserEntity>,
    @SerializedName("popular_cards")
    val cards: List<CardEntity>

)

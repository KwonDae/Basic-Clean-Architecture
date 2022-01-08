package com.daewon.data.response

import com.daewon.data.entity.CardEntity
import com.daewon.data.entity.UserEntity
import com.google.gson.annotations.SerializedName

data class PhotoDetailResponse (
    @SerializedName("ok")
    val status: Boolean,
    @SerializedName("card")
    val card: CardEntity,
    @SerializedName("user")
    val user: UserEntity,
    @SerializedName("recommend_cards")
    val recommendCards: List<CardEntity>
)
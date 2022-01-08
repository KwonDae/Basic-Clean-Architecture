package com.daewon.data.response

import com.daewon.data.entity.CardEntity
import com.google.gson.annotations.SerializedName

data class PhotoFeedResponse(
    @SerializedName("ok")
    val status: Boolean,
    @SerializedName("cards")
    val cards: List<CardEntity>
)
package com.daewon.domain.model

data class PhotoDetail(
    val status: Boolean,
    val card: Card,
    val user: User,
    val recommendCards: List<Card>
)
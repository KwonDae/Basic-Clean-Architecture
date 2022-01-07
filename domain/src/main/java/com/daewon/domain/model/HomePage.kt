package com.daewon.domain.model

data class HomePage(
    val status: Boolean,
    val cards: List<Card>,
    val users: List<User>
)

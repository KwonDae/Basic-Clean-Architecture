package com.daewon.domain.model

data class SignInRes(
    val status: Boolean,
    val userId: Int?,
    val errorMsg: String?

)
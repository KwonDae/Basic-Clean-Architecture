package com.daewon.data.response

import com.google.gson.annotations.SerializedName

data class SignInResponse(
    @SerializedName("ok")
    val status: Boolean,
    @SerializedName("error_msg")
    val errorMsg: String?,
    @SerializedName("user_id")
    val userId: Int?
)

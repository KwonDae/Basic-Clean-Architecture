package com.daewon.data.service

import com.daewon.data.response.HomePageResponse
import com.daewon.data.entity.UserEntity
import com.daewon.data.response.SignInResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    // SignIn
    @POST("users/sign_in")
    suspend fun signIn(@Body user: UserEntity): SignInResponse

    @GET("/home")
    suspend fun getHomePageData(): HomePageResponse
}
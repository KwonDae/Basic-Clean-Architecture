package com.daewon.data.service

import com.daewon.data.response.HomePageResponse
import com.daewon.data.entity.UserEntity
import com.daewon.data.response.PhotoDetailResponse
import com.daewon.data.response.SignInResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    // SignIn
    @POST("users/sign_in")
    suspend fun signIn(@Body user: UserEntity): SignInResponse

    @GET("home")
    suspend fun getHomePageData(): HomePageResponse

    @GET("cards/{cardId}")
    suspend fun getPhotoDetailData(@Path("cardId")cardId: Int) : PhotoDetailResponse

}
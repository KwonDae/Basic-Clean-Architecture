package com.daewon.data.service

import com.daewon.data.response.HomePageResponse
import com.daewon.data.entity.UserEntity
import com.daewon.data.response.PhotoDetailResponse
import com.daewon.data.response.PhotoFeedResponse
import com.daewon.data.response.SignInResponse
import retrofit2.http.*

interface ApiService {
    // 로그인
    @POST("users/sign_in")
    suspend fun signIn(@Body user: UserEntity): SignInResponse

    // 홈 데이터 가져오기
    @GET("home")
    suspend fun getHomePageData(): HomePageResponse

    // 사진상세 데이터 가져오기
    @GET("cards/{cardId}")
    suspend fun getPhotoDetailData(@Path("cardId") cardId: Int): PhotoDetailResponse

    // 사진피드 데이터 가져오기
    @GET("cards")
    suspend fun getPhotoFeed(@Query("page") page: Int): PhotoFeedResponse

}
package com.daewon.data.remote

import com.daewon.data.response.HomePageResponse
import com.daewon.data.response.SignInResponse
import com.daewon.data.entity.UserEntity
import com.daewon.data.response.PhotoDetailResponse
import com.daewon.data.response.PhotoFeedResponse

interface CardRemoteDataSource {
    suspend fun signIn(user: UserEntity): SignInResponse
    suspend fun getHomePageData(): HomePageResponse
    suspend fun getPhotoDetailData(cardId: Int): PhotoDetailResponse
    suspend fun getPhotoFeedData(page: Int): PhotoFeedResponse
}
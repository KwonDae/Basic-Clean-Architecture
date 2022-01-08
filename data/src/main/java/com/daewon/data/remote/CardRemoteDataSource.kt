package com.daewon.data.remote

import com.daewon.data.response.HomePageResponse
import com.daewon.data.response.SignInResponse
import com.daewon.data.entity.UserEntity
import com.daewon.data.response.PhotoDetailResponse

interface CardRemoteDataSource {
    suspend fun signIn(user: UserEntity): SignInResponse
    suspend fun getHomePageData(): HomePageResponse
    suspend fun getPhotoDetail(cardId: Int): PhotoDetailResponse
}
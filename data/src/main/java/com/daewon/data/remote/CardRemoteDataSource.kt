package com.daewon.data.remote

import com.daewon.data.response.HomePageResponse
import com.daewon.data.response.SignInResponse
import com.daewon.data.entity.UserEntity

interface CardRemoteDataSource {
    suspend fun signIn(user: UserEntity): SignInResponse
    suspend fun getHomePageData(): HomePageResponse
}
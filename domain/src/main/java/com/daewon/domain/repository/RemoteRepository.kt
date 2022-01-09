package com.daewon.domain.repository

import com.daewon.domain.model.*
import kotlinx.coroutines.flow.Flow

interface RemoteRepository {
    suspend fun signIn(signIn: SignIn): SignInRes
    suspend fun getHomePageData() : HomePage
    suspend fun getPhotoDetailData(
        cardId: Int
    ): PhotoDetail

    fun <T> getPhotoFeedData(
        page: Int
    ): Flow<T>
}
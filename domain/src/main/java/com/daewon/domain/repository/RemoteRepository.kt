package com.daewon.domain.repository

import com.daewon.domain.model.Card
import com.daewon.domain.model.HomePage
import com.daewon.domain.model.PhotoDetail
import com.daewon.domain.model.PhotoFeed
import kotlinx.coroutines.flow.Flow

interface RemoteRepository {
    suspend fun getHomePageData() : HomePage
    suspend fun getPhotoDetailData(
        cardId: Int
    ): PhotoDetail

    fun <T> getPhotoFeedData(
        page: Int
    ): Flow<T>
}
package com.daewon.domain.repository

import com.daewon.domain.model.Card
import com.daewon.domain.model.HomePage
import com.daewon.domain.model.PhotoDetail

interface RemoteRepository {
    suspend fun getHomePageData() : HomePage
    suspend fun getPhotoDetailData(
        cardId: Int
    ): PhotoDetail
}
package com.daewon.domain.repository

import com.daewon.domain.model.Card
import com.daewon.domain.model.HomePage

interface RemoteRepository {
    suspend fun getHomePageData() : HomePage

}
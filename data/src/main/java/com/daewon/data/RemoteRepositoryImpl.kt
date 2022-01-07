package com.daewon.data

import com.daewon.data.mapper.mapperToHomePageData
import com.daewon.data.remote.CardRemoteDataSource
import com.daewon.domain.model.HomePage
import com.daewon.domain.repository.RemoteRepository
import javax.inject.Inject

class RemoteRepositoryImpl @Inject constructor(
    private val cardRemoteDataSource: CardRemoteDataSource
): RemoteRepository{

    override suspend fun getHomePageData(): HomePage {
        val result = cardRemoteDataSource.getHomePageData()
        return mapperToHomePageData(result)
    }

}
package com.daewon.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.daewon.data.mapper.mapperToHomePageData
import com.daewon.data.mapper.mapperToPhotoDetailData
import com.daewon.data.mapper.mapperToPhotoFeedData
import com.daewon.data.remote.CardRemoteDataSource
import com.daewon.data.service.ApiService
import com.daewon.domain.model.HomePage
import com.daewon.domain.model.PhotoDetail
import com.daewon.domain.model.PhotoFeed
import com.daewon.domain.repository.RemoteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RemoteRepositoryImpl @Inject constructor(
    private val cardRemoteDataSource: CardRemoteDataSource,
    private val apiService: ApiService
) : RemoteRepository {

    companion object {
        private const val NETWORK_PAGE_SIZE = 20
    }

    override suspend fun getHomePageData(): HomePage {
        val result = cardRemoteDataSource.getHomePageData()
        return mapperToHomePageData(result)
    }

    override suspend fun getPhotoDetailData(cardId: Int): PhotoDetail {
        val result = cardRemoteDataSource.getPhotoDetailData(cardId)
        return mapperToPhotoDetailData(result)
    }

    override fun <T> getPhotoFeedData(page: Int): Flow<T> =
        Pager(
            config = PagingConfig(
                enablePlaceholders = false,
                pageSize = NETWORK_PAGE_SIZE
            ),
            pagingSourceFactory = { PhotoPagingSource(service = apiService) }
        ).flow as Flow<T>

//        val result = cardRemoteDataSource.getPhotoFeedData(page)
//        return mapperToPhotoFeedData(result)
}
package com.daewon.data

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.daewon.data.mapper.mapperToHomePageData
import com.daewon.data.mapper.mapperToPhotoDetailData
import com.daewon.data.remote.CardRemoteDataSource
import com.daewon.data.api.ApiService
import com.daewon.data.db.CardDatabase
import com.daewon.data.signin.SignInRemoteDataSource
import com.daewon.domain.model.*
import com.daewon.domain.repository.RemoteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import kotlin.math.sign

class RemoteRepositoryImpl @Inject constructor(
    private val cardRemoteDataSource: CardRemoteDataSource,
    private val signInRemoteDataSource: SignInRemoteDataSource,
    private val apiService: ApiService,
    private val cardDatabase: CardDatabase
) : RemoteRepository {

    companion object {
        private const val NETWORK_PAGE_SIZE = 20
    }

    override suspend fun signIn(signIn: SignIn): SignInRes {
        val result = signInRemoteDataSource.signIn(signIn)
        return SignInRes(
            result.status,
            result.userId,
            result.errorMsg
        )
    }

    override suspend fun getHomePageData(): HomePage {
        val result = cardRemoteDataSource.getHomePageData()
        return mapperToHomePageData(result)
    }

    override suspend fun getPhotoDetailData(cardId: Int): PhotoDetail {
        val result = cardRemoteDataSource.getPhotoDetailData(cardId)
        return mapperToPhotoDetailData(result)
    }

//    override fun <T> getPhotoFeedData(page: Int): Flow<T> =
//        Pager(
//            config = PagingConfig(
//                enablePlaceholders = false,
//                pageSize = NETWORK_PAGE_SIZE
//            ),
//            pagingSourceFactory = { PhotoPagingSource(service = apiService) }
//        ).flow as Flow<T>


//     RemoteMediator
    override fun <T> getPhotoFeedData(page: Int): Flow<T> {
        val pagingSourceFactory = { cardDatabase.cardsDao().searchAllCards() }
        @OptIn(ExperimentalPagingApi::class)
        return Pager(
            config = PagingConfig(
                enablePlaceholders = false,
                pageSize = NETWORK_PAGE_SIZE
            ),
            remoteMediator = PhotoRemoteMediator(
                apiService,
                cardDatabase
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow as Flow<T>
    }
}
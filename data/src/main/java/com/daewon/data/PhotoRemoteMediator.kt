package com.daewon.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.daewon.data.api.ApiService
import com.daewon.data.db.CardDatabase
import com.daewon.data.entity.RemoteKeys
import com.daewon.domain.model.Card
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

/**
 * API 통신 + Database 으로 페이징 데이터 소스 가져오기
*/
const val PHOTO_STARTING_PAGE_INDEX = 1
const val PHOTO_PER_PAGE_INDEX = 20

@OptIn(ExperimentalPagingApi::class)
class PhotoRemoteMediator @Inject constructor(
    private val apiService: ApiService,
    private val cardDatabase: CardDatabase
) : RemoteMediator<Int, Card>() {

    override suspend fun initialize(): InitializeAction {
        // 처음 페이지가 불릴 떄 LoadType.REFRESH 호출하도록
        return InitializeAction.LAUNCH_INITIAL_REFRESH
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Card>
    ): MediatorResult {

        val page = when (loadType) {
            LoadType.REFRESH -> {
                PHOTO_STARTING_PAGE_INDEX
            }

            LoadType.PREPEND -> {
                return MediatorResult.Success(endOfPaginationReached = true)
            }

            LoadType.APPEND -> {
                val remoteKeys = getRemoteKeyForLastItem(state)
                val nextKey = remoteKeys?.nextKey
                if (nextKey == null) {
                    return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                }
                nextKey
            }

        }

        try {
            val apiResponse = apiService.getPhotoFeed(page, PHOTO_PER_PAGE_INDEX)

            val cards = apiResponse.cards
            val endOfPaginationReached = cards.isNullOrEmpty()
            cardDatabase.withTransaction {
                // REFRESH 할 떄 기존에 있던 테이블 초기화
                if (loadType == LoadType.REFRESH) {
                    cardDatabase.remoteKeysDao().clearRemoteKeys()
                    cardDatabase.cardsDao().clearCards()
                }

                val prevKey = if( page == PHOTO_STARTING_PAGE_INDEX) null else page.minus(1)
                val nextKey = if(endOfPaginationReached) null else page.plus(1)
                val keys = cards.map {
                    RemoteKeys(id = it.id, prevKey = prevKey, nextKey = nextKey)
                }
                cardDatabase.remoteKeysDao().insertAll(keys)
                cardDatabase.cardsDao().insertAll(cards)
            }
            return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)


        } catch (exception: IOException) {
            return MediatorResult.Error(exception)
        } catch (exception: HttpException) {
            return MediatorResult.Error(exception)
        }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, Card>): RemoteKeys? {
        // 마지막 페이지, 마지막 인덱스 아이템을 찾아오기
        return state.pages.lastOrNull() {
            it.data.isNotEmpty()
        }?.data?.lastOrNull()
            ?.let { card ->
                // 마지막 페이지의 마지막 아이템의 key 값 가져오기
                cardDatabase.remoteKeysDao().remoteKeysId(card.id)
            }
    }

}
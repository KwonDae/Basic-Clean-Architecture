package com.daewon.data

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.daewon.data.api.ApiService
import com.daewon.data.db.CardDatabase
import com.daewon.data.entity.CardEntity
import com.daewon.data.entity.RemoteKeys
import com.daewon.domain.model.Card
import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception
import javax.inject.Inject

/**
 * 참고:RemoteMediator API는 현재 실험용입니다.
 * RemoteMediator를 구현하는 모든 클래스에는
 * @OptIn(ExperimentalPagingApi::class)로 주석을 추가해야 합니다.
 */
@OptIn(ExperimentalPagingApi::class)
class PhotoRemoteMediator @Inject constructor(
    private val service: ApiService,
    private val cardDatabase: CardDatabase
) : RemoteMediator<Int, Card>() {

    override suspend fun initialize(): InitializeAction {
        // Launch remote refresh as soon as paging starts and do not trigger remote prepend or
        // append until refresh has succeeded. In cases where we don't mind showing out-of-date,
        // cached offline data, we can return SKIP_INITIAL_REFRESH instead to prevent paging
        // triggering remote refresh.
        return InitializeAction.LAUNCH_INITIAL_REFRESH
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Card>
    ): MediatorResult {

        val page = when (loadType) {
            LoadType.REFRESH -> {
                val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                remoteKeys?.nextKey?.minus(1) ?: PHOTO_STARTING_PAGE_INDEX
            }

            LoadType.PREPEND -> {
                val remoteKeys = getRemoteKeyForFirstItem(state)
                val prevKey = remoteKeys?.prevKey
                if (prevKey == null) {
                    return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                }
                prevKey
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
            val apiResponse = service.getPhotoFeed(page)

            val cards = apiResponse.cards
            val endOfPaginationReached = cards.isNullOrEmpty()
            cardDatabase.withTransaction {
                // clear all tables in the database
                if (loadType == LoadType.REFRESH) {
                    cardDatabase.remoteKeysDao().clearRemoteKeys()
                    cardDatabase.cardsDao().clearCards()
                }

                val prevKey = if( page == PHOTO_STARTING_PAGE_INDEX) null else page.minus(1)
                val nextKey = if( endOfPaginationReached) null else page.plus(1)
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

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, Card>): RemoteKeys? {
        // Get the first page that was retrieved, that contained items.
        // From that first page, get the first item
        return state.pages.firstOrNull {
            it.data.isNotEmpty()
        }?.data?.firstOrNull()
            ?.let { card ->
                // Get the remote keys of the first items retrieved
                Log.d("로그", "getRemoteKeyForFirstItem: ${card.id}")
                cardDatabase.remoteKeysDao().remoteKeysId(card.id)
            }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, Card>): RemoteKeys? {
        // Get the last page that was retrieved, that contained items.
        // From that last page, get the last item
        return state.pages.lastOrNull() {
            it.data.isNotEmpty()
        }?.data?.lastOrNull()
            ?.let { card ->
                // Get the remote keys of the last item retrieved
                Log.d("로그", "getRemoteKeyForLastItem: ${card.id}")
                cardDatabase.remoteKeysDao().remoteKeysId(card.id)
            }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, Card>
    ): RemoteKeys? {
        // The paging library is trying to load data after the anchor position
        // Get the item closest to the anchor position
        return state.anchorPosition?.let {
                position ->
            state.closestItemToPosition(position)?.id?.let { cardId ->
                Log.d("로그", "getRemoteKeyClosestToCurrentPosition: ${cardId}")
                cardDatabase.remoteKeysDao().remoteKeysId(cardId)
            }
        }
    }

}
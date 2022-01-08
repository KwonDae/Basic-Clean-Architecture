package com.daewon.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.daewon.data.entity.CardEntity
import com.daewon.data.mapper.mapperToListCard
import com.daewon.data.service.ApiService
import com.daewon.domain.model.Card
import javax.inject.Inject

private const val PHOTO_STARTING_PAGE_INDEX = 1

class PhotoPagingSource @Inject constructor(
    private val service: ApiService,
): PagingSource<Int, Card>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Card> {
        return try {
            val nextPageNumber = params.key ?: PHOTO_STARTING_PAGE_INDEX
            val response = service.getPhotoFeed(page = nextPageNumber)

            if(response.cards.isNullOrEmpty()){
                return LoadResult.Error(Exception())
            }

            LoadResult.Page(
                data = mapperToListCard(response.cards),
                prevKey = null, // Only paging forward
                nextKey = nextPageNumber + 1
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }
    override fun getRefreshKey(state: PagingState<Int, Card>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey
        }
    }

}
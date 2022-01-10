package com.daewon.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.daewon.data.mapper.mapperToListCard
import com.daewon.data.api.ApiService
import com.daewon.domain.model.Card
import javax.inject.Inject

/**
 * API 통신으로만 페이징 데이터 소스 가져오기
 */
class PhotoPagingSource @Inject constructor(
    private val apiService: ApiService,
): PagingSource<Int, Card>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Card> {
        return try {
            val nextPageNumber = params.key ?: PHOTO_STARTING_PAGE_INDEX
            val response = apiService.getPhotoFeed(page = nextPageNumber, PHOTO_PER_PAGE_INDEX)

            // page6부터는 []로 넘어온다.
            // Exception 처리
            // nextKey = if(page == response.totalPages) null else page + 1 과 같은 기능
            if(response.cards.isNullOrEmpty()){
                return LoadResult.Error(Exception())
            }

            LoadResult.Page(
                data = mapperToListCard(response.cards),
                prevKey = if(nextPageNumber == PHOTO_STARTING_PAGE_INDEX) null else nextPageNumber - 1, // Only paging forward
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
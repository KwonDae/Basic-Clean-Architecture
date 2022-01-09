package com.daewon.presentation.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.daewon.domain.model.Card
import com.daewon.domain.usecase.GetPhotoFeedUseCase
import com.daewon.presentation.adapter.PhotoFeedAdapter
import com.daewon.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class PhotoFeedViewModel @Inject constructor(
    private val getPhotoFeedUseCase: GetPhotoFeedUseCase
) : BaseViewModel() {

    companion object {
        const val FIRST_PAGE = 1
    }

    private var currentPageValue: Int? = null
    private var _currentSearchResult: Flow<PagingData<Card>>? = null

    private val photoFeedAdapter = PhotoFeedAdapter()
    fun getAdapter() = photoFeedAdapter

    val isRefreshLoading = MutableLiveData<Boolean>()

    fun searchPhoto(): Flow<PagingData<Card>> {
        isRefreshLoading.value = true
        val newResult = getPhotoFeedUseCase
            .execute<PagingData<Card>>(page = FIRST_PAGE)
            .cachedIn(viewModelScope)
        _currentSearchResult = newResult
        isRefreshLoading.value = false
        return newResult
    }


//    fun getPhotoFeed(page: Int): Flow<PagingData<PhotoFeed>> {
//        currentPageValue = page
//        val newResult: Flow<PagingData<PhotoFeed>> = getPhotoFeedUseCase(page =  currentPageValue, scope = viewModelScope) {
//            it as Flow<PagingData<PhotoFeed>>
//        }
//    }
}
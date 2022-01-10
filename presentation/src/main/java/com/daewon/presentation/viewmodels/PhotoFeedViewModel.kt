package com.daewon.presentation.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.daewon.domain.model.Card
import com.daewon.domain.usecase.GetPhotoFeedUseCase
import com.daewon.presentation.adapter.PHOTO_FEED_INDEX
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

    val isRefreshLoading = MutableLiveData<Boolean>()

    fun searchPhoto(): Flow<PagingData<Card>> {
        isRefreshLoading.value = true
        val newResult = getPhotoFeedUseCase
            .execute<PagingData<Card>>(page = FIRST_PAGE)
            .cachedIn(viewModelScope)
        isRefreshLoading.value = false
        return newResult
    }

}
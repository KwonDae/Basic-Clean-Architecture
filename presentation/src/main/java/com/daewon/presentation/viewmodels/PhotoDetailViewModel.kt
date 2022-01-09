package com.daewon.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.daewon.domain.model.HomePage
import com.daewon.domain.model.PhotoDetail
import com.daewon.domain.usecase.GetPhotoDetailUseCase
import com.daewon.presentation.adapter.RecommendPhotoAdapter
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PhotoDetailViewModel @Inject constructor(
    private val getPhotoDetailUseCase: GetPhotoDetailUseCase
): ViewModel() {

    private val _toastMsg = MutableLiveData<String>()
    private val _photoDetailData = MutableLiveData<PhotoDetail>()
    val toastMsg: LiveData<String> = _toastMsg
    val photoDetailData: LiveData<PhotoDetail> = _photoDetailData

    val isRefreshLoading = MutableLiveData<Boolean>()

    private val recommendPhotoAdapter = RecommendPhotoAdapter()

    fun getAdapter() = recommendPhotoAdapter

    fun getPhotoDetail(cardId: Int) {
        isRefreshLoading.value = true
        getPhotoDetailUseCase(cardId = cardId, scope =  viewModelScope) {
            if(it.status) {
                _photoDetailData.value = it
                isRefreshLoading.value = false
            } else {
                _toastMsg.value = "네트워크가 원할하지 않습니다."
                isRefreshLoading.value = false
            }
        }
    }

}
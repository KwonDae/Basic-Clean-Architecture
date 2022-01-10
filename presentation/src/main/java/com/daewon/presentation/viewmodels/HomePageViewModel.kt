package com.daewon.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.daewon.domain.model.HomePage
import com.daewon.domain.usecase.GetHomePageDataUseCase
import com.daewon.presentation.adapter.HomeCardAdapter
import com.daewon.presentation.adapter.HomeUserAdapter
import com.daewon.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomePageViewModel @Inject constructor(
    private val getHomePageDataUseCase: GetHomePageDataUseCase
) : BaseViewModel() {

    private val _toastMsg = MutableLiveData<String>()
    private val _homePageData = MutableLiveData<HomePage>()
    val toastMsg: LiveData<String> = _toastMsg
    val homePageData: LiveData<HomePage> = _homePageData

    private val cardAdapter = HomeCardAdapter()
    private val userAdapter = HomeUserAdapter()

    fun getCardAdapter() = cardAdapter
    fun getUserAdapter() = userAdapter

    val isRefreshLoading = MutableLiveData<Boolean>()

    fun getHomePage() {
        try{
        isRefreshLoading.value = true
        getHomePageDataUseCase(viewModelScope) {
            if(it.status) {
                _homePageData.value = it
//                _toastMsg.value = "홈 데이터를 불러왔습니다. \uD83D\uDE80"
                isRefreshLoading.value = false
            } else {
                _toastMsg.value = "네트워크가 원할하지 않습니다."
                isRefreshLoading.value = false
            }
        }
        }catch (e: Exception) {
            e.printStackTrace()
        }
    }

}
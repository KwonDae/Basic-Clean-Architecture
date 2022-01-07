package com.daewon.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.daewon.domain.model.HomePage
import com.daewon.domain.usecase.GetHomePageDataUseCase
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

    fun getHomePageData() {
        getHomePageDataUseCase(viewModelScope) {
            if(it.status) {
                _homePageData.value = it
            }
        }
    }

}
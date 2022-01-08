package com.daewon.data.remote

import com.daewon.data.entity.UserEntity
import com.daewon.data.response.HomePageResponse
import com.daewon.data.response.PhotoDetailResponse
import com.daewon.data.response.SignInResponse
import com.daewon.data.service.ApiService
import javax.inject.Inject

class CardRemoteDataSourceImpl @Inject constructor(private val apiService: ApiService) :
    CardRemoteDataSource {
    override suspend fun signIn(user: UserEntity): SignInResponse {
        return apiService.signIn(user)
    }

    override suspend fun getHomePageData(): HomePageResponse {
        return apiService.getHomePageData()
    }

    override suspend fun getPhotoDetail(cardId: Int): PhotoDetailResponse {
        return apiService.getPhotoDetailData(cardId)
    }
}
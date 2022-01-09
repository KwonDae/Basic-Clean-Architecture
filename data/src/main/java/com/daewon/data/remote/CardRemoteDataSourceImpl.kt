package com.daewon.data.remote

import com.daewon.data.entity.UserEntity
import com.daewon.data.response.HomePageResponse
import com.daewon.data.response.PhotoDetailResponse
import com.daewon.data.response.PhotoFeedResponse
import com.daewon.data.response.SignInResponse
import com.daewon.data.api.ApiService
import javax.inject.Inject

class CardRemoteDataSourceImpl @Inject constructor(private val apiService: ApiService) :
    CardRemoteDataSource {
    override suspend fun signIn(user: UserEntity): SignInResponse {
        return apiService.signIn(user)
    }

    override suspend fun getHomePageData(): HomePageResponse {
        return apiService.getHomePageData()
    }

    override suspend fun getPhotoDetailData(cardId: Int): PhotoDetailResponse {
        return apiService.getPhotoDetailData(cardId)
    }

    override suspend fun getPhotoFeedData(page: Int): PhotoFeedResponse {
        return apiService.getPhotoFeed(page)
    }
}
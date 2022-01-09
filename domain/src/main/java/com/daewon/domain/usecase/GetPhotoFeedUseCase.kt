package com.daewon.domain.usecase

import com.daewon.domain.repository.RemoteRepository
import kotlinx.coroutines.flow.Flow

class GetPhotoFeedUseCase(private val remoteRepository: RemoteRepository) {

    fun <T> execute(page: Int): Flow<T> = remoteRepository.getPhotoFeedData(page)

}
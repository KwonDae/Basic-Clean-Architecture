package com.daewon.domain.usecase

import com.daewon.domain.repository.RemoteRepository

class GetPhotoFeedUseCase(private val remoteRepository: RemoteRepository) {

    fun <T> execute(page: Int) = remoteRepository.getPhotoFeedData<T>(page)

//    operator fun <T> invoke(
//        page: Int,
//        scope: CoroutineScope,
//        onResult: (Flow<T>) -> Unit
//    ) {
//        scope.launch(Dispatchers.Main) {
//            val result = withContext(Dispatchers.IO) {
//                remoteRepository.getPhotoFeedData<T>(page = page)
//            }
//            onResult(result)
//        }
//
//    }


}
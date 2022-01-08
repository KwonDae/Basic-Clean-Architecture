package com.daewon.domain.usecase

import com.daewon.domain.model.HomePage
import com.daewon.domain.model.PhotoDetail
import com.daewon.domain.repository.RemoteRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GetPhotoDetailUseCase(private val remoteRepository: RemoteRepository) {

    operator fun invoke(
        cardId: Int,
        scope: CoroutineScope,
        onResult: (PhotoDetail) -> Unit
    ) {
        scope.launch(Dispatchers.Main) {
            val result = withContext(Dispatchers.IO) {
                remoteRepository.getPhotoDetailData(cardId = cardId)
            }
            onResult(result)
        }
    }
}
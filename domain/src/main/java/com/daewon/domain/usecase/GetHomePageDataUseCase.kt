package com.daewon.domain.usecase

import com.daewon.domain.model.HomePage
import com.daewon.domain.repository.RemoteRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetHomePageDataUseCase @Inject constructor(private val remoteRepository: RemoteRepository) {

    operator fun invoke(
        scope: CoroutineScope,
        onResult: (HomePage) -> Unit
    ) {
        scope.launch(Dispatchers.Main) {
            val result = withContext(Dispatchers.IO) {
                remoteRepository.getHomePageData()
            }
            onResult(result)
        }
    }
}
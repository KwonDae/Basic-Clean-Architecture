package com.daewon.domain.usecase

import com.daewon.domain.repository.RemoteRepository
import kotlinx.coroutines.CoroutineScope

class GetLoginUseCase(private val remoteRepository: RemoteRepository) {

    operator fun invoke(
        id: String,
        pw: String,
        scope: CoroutineScope
    ) {
    }
}
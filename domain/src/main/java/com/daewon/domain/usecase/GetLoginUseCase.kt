package com.daewon.domain.usecase

import com.daewon.domain.model.SignIn
import com.daewon.domain.model.SignInRes
import com.daewon.domain.repository.RemoteRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GetLoginUseCase(private val remoteRepository: RemoteRepository) {

    operator fun invoke(
        id: String,
        pw: String,
        scope: CoroutineScope,
        onResult: (SignInRes) -> Unit
    ) {
        scope.launch(Dispatchers.Main) {
            val result = withContext(Dispatchers.IO) {
                remoteRepository.signIn(SignIn(id,pw))
            }
            onResult(result)
        }
    }
}
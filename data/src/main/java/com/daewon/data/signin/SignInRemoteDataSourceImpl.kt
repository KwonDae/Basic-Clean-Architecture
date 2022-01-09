package com.daewon.data.signin

import com.daewon.data.api.ApiService
import com.daewon.data.response.SignInResponse
import com.daewon.domain.model.SignIn
import javax.inject.Inject

class SignInRemoteDataSourceImpl @Inject constructor(
    private val apiService: ApiService
) : SignInRemoteDataSource {
    override suspend fun signIn(signIn: SignIn): SignInResponse {
        return apiService.signIn(signIn)
    }
}
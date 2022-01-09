package com.daewon.data.signin

import com.daewon.data.response.SignInResponse
import com.daewon.domain.model.SignIn

interface SignInRemoteDataSource {
    suspend fun signIn(signIn: SignIn): SignInResponse
}
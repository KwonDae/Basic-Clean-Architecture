package com.daewon.ohouseapp.di

import com.daewon.data.remote.CardRemoteDataSource
import com.daewon.data.remote.CardRemoteDataSourceImpl
import com.daewon.data.signin.SignInRemoteDataSource
import com.daewon.data.signin.SignInRemoteDataSourceImpl
import com.daewon.presentation.signin.EncryptedFileAuthenticator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Singleton
    @Provides
    fun provideCardRemoteDataSource(source: CardRemoteDataSourceImpl): CardRemoteDataSource {
        return source
    }

    @Singleton
    @Provides
    fun provideSignInRemoteDataSource(source: SignInRemoteDataSourceImpl): SignInRemoteDataSource {
        return source
    }

}
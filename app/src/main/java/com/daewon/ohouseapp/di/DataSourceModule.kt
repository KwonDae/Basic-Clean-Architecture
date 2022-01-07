package com.daewon.ohouseapp.di

import com.daewon.data.remote.CardRemoteDataSource
import com.daewon.data.remote.CardRemoteDataSourceImpl
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
}
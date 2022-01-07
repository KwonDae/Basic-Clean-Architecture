package com.daewon.ohouseapp.di

import com.daewon.data.RemoteRepositoryImpl
import com.daewon.domain.repository.RemoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideRemoteRepository(repository: RemoteRepositoryImpl): RemoteRepository {
        return repository
    }
}
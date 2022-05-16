package com.daewon.ohouseapp.di

import com.daewon.domain.repository.RemoteRepository
import com.daewon.domain.usecase.GetHomePageDataUseCase
import com.daewon.domain.usecase.GetLoginUseCase
import com.daewon.domain.usecase.GetPhotoDetailUseCase
import com.daewon.domain.usecase.GetPhotoFeedUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

//    @Singleton
//    @Provides
//    fun provideGetHomePageDataUseCase(repository: RemoteRepository): GetHomePageDataUseCase {
//        return GetHomePageDataUseCase(repository)
//    }
//
//    @Singleton
//    @Provides
//    fun provideGetLoginUseCase(repository: RemoteRepository): GetLoginUseCase {
//        return GetLoginUseCase(repository)
//    }
//
//    @Singleton
//    @Provides
//    fun provideGetPhotoDetailUseCase(repository: RemoteRepository): GetPhotoDetailUseCase {
//        return GetPhotoDetailUseCase(repository)
//    }
//
//    @Singleton
//    @Provides
//    fun provideGetPhotoFeedUseCase(repository: RemoteRepository): GetPhotoFeedUseCase {
//        return GetPhotoFeedUseCase(repository)
//    }
}
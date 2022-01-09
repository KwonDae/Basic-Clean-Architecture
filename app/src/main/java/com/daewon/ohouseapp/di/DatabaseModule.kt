package com.daewon.ohouseapp.di

import android.content.Context
import com.daewon.data.db.CardDao
import com.daewon.data.db.CardDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Singleton
    @Provides
    fun provideCardDatabase(@ApplicationContext context: Context): CardDatabase {
        return CardDatabase.getInstance(context)
    }

    @Provides
    fun provideCardDao(cardDatabase: CardDatabase): CardDao {
        return cardDatabase.cardsDao()
    }
}
package com.daewon.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.daewon.data.entity.CardEntity
import com.daewon.data.entity.RemoteKeys
import com.daewon.domain.model.Card

@Database(
    entities = [CardEntity::class, RemoteKeys::class],
    version = 1,
    exportSchema = false
)
abstract class CardDatabase: RoomDatabase() {

    abstract fun cardsDao(): CardDao
    abstract fun remoteKeysDao(): RemoteKeysDao

    companion object {

        // FOr Singleton instantiation
        @Volatile
        private var instance: CardDatabase? = null

        fun getInstance(context: Context): CardDatabase =
            instance ?: synchronized(this) {
                instance
                    ?: buildDatabase(context = context).also {
                        instance = it
                    }
            }

        private fun buildDatabase(context: Context): CardDatabase =
            Room.databaseBuilder(context.applicationContext,
                CardDatabase::class.java, "Card.db")
                .build()
    }
}
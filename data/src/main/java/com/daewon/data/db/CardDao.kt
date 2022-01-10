package com.daewon.data.db

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.daewon.data.entity.CardEntity
import com.daewon.domain.model.Card

@Dao
interface CardDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(cards: List<CardEntity>)

    @Query("SELECT * FROM card ORDER BY id DESC")
    fun searchAllCards(): PagingSource<Int, Card>

    @Query("DELETE FROM card")
    suspend fun clearCards()

}
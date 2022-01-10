package com.daewon.data.db

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.daewon.data.entity.CardEntity
import com.daewon.data.mapper.mapperToCard
import com.daewon.data.mapper.mapperToCardEntity
import com.daewon.domain.model.Card

class CardConverter {
    @TypeConverter
    fun entityToModel(cardEntity: CardEntity): Card? {
        return mapperToCard(cardEntity = cardEntity)
    }

//    @TypeConverter
//    fun modelToEntity(card: Card): CardEntity? {
//        return mapperToCardEntity(card = card)
//    }
}
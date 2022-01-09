package com.daewon.data.mapper

import com.daewon.data.entity.CardEntity
import com.daewon.data.entity.UserEntity
import com.daewon.data.response.HomePageResponse
import com.daewon.data.response.PhotoDetailResponse
import com.daewon.data.response.PhotoFeedResponse
import com.daewon.domain.model.*

fun mapperToHomePageData(homePageResponse: HomePageResponse): HomePage {
    return HomePage(
        homePageResponse.status,
        mapperToListCard(homePageResponse.cards),
        mapperToListUser(homePageResponse.users)
    )
}

fun mapperToPhotoDetailData(photoDetailResponse: PhotoDetailResponse): PhotoDetail {
    return PhotoDetail(
        photoDetailResponse.status,
        mapperToCard(photoDetailResponse.card),
        mapperToUser(photoDetailResponse.user),
        mapperToListCard(photoDetailResponse.recommendCards)
    )
}

fun mapperToPhotoFeedData(photoFeedResponse: PhotoFeedResponse): PhotoFeed {
    return PhotoFeed(
        photoFeedResponse.status,
        mapperToListCard(photoFeedResponse.cards)
    )
}

fun mapperToListCard(cards: List<CardEntity>): List<Card> {
    return cards.toList().map {
        Card(
            it.userId,
            it.imgUrl,
            it.description,
            it.id
        )
    }
}

fun mapperToCard(cardEntity: CardEntity): Card {
    return Card(
        cardEntity.userId,
        cardEntity.imgUrl,
        cardEntity.description,
        cardEntity.id
    )
}

fun mapperToCardEntity(card: Card): CardEntity {
    return CardEntity(
        card.userId,
        card.imgUrl,
        card.description,
        card.id
    )
}

fun mapperToListUser(users: List<UserEntity>): List<User> {
    return users.toList().map {
        User(
            it.nickname,
            it.introduce,
            it.id
        )
    }
}

fun mapperToUser(userEntity: UserEntity): User {
    return User(
        userEntity.nickname,
        userEntity.introduce,
        userEntity.id,
    )
}
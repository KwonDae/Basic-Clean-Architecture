package com.daewon.data.mapper

import com.daewon.data.entity.CardEntity
import com.daewon.data.entity.UserEntity
import com.daewon.data.response.HomePageResponse
import com.daewon.data.response.PhotoDetailResponse
import com.daewon.domain.model.Card
import com.daewon.domain.model.HomePage
import com.daewon.domain.model.PhotoDetail
import com.daewon.domain.model.User

fun mapperToHomePageData(homePageResponse: HomePageResponse): HomePage {
    return HomePage(
        homePageResponse.status,
        mapperToCard(homePageResponse.cards),
        mapperToUser(homePageResponse.users)
    )
}

fun mapperToPhotoDetailData(photoDetailResponse: PhotoDetailResponse): PhotoDetail {
    return PhotoDetail(
        photoDetailResponse.status,
        Card(
            photoDetailResponse.card.userId,
            photoDetailResponse.card.imgUrl,
            photoDetailResponse.card.description,
            photoDetailResponse.card.id
        ),
        User(
            photoDetailResponse.user.nickname,
            photoDetailResponse.user.introduce,
            photoDetailResponse.user.id
        ),
        mapperToCard(photoDetailResponse.recommendCards)
    )
}

fun mapperToCard(cards: List<CardEntity>): List<Card> {
    return cards.toList().map {
        Card(
            it.userId,
            it.imgUrl,
            it.description,
            it.id
        )
    }
}

fun mapperToUser(users: List<UserEntity>): List<User> {
    return users.toList().map {
        User(
            it.nickname,
            it.introduce,
            it.id
        )
    }
}
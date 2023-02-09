package kr.co.younhwan.eatjnu.data.remote.dto

import kr.co.younhwan.eatjnu.domain.model.Place

data class PlaceInfo(
    val id: Int,
    val name: String,
    val image: String?,
    val reviewCount: Int,
    val likeCount: Int,
    val tags: String?,
    val filter: String,
)

fun PlaceInfo.toPlace(): Place {
    return Place(
        id = id,
        name = name,
        image = image,
        reviewCount = reviewCount,
        likeCount = likeCount,
        tags = tags,
        filter = filter
    )
}
package kr.co.younhwan.eatjnu.data.remote.dto

import kr.co.younhwan.eatjnu.domain.model.PlaceInfo

data class PlaceDto(
    val id: Int?,
    val name: String?,
    val image: String?,
    val reviewCount: Int?,
    val likeCount: Int?,
    val tags: String?,
    val filter: String?,
)

fun PlaceDto.toPlaceInfo(): PlaceInfo {
    return PlaceInfo(
        id = id ?: -1,
        name = name ?: "",
        image = image ?: "",
        reviewCount = reviewCount ?: 0,
        likeCount = likeCount ?: 0,
        tags = tags ?: "",
        filter = filter ?: "맛집"
    )
}
package kr.co.younhwan.eatjnu.data.remote.dto

import kr.co.younhwan.eatjnu.domain.model.PlaceDetailInfo

data class PlaceDetailDto(
    val id: Int,
    val name: String,
    val likeCount: Int?,
    val reviewCount: Int?,
    val filter: String,
    val tags: String?,
    val image: String?,
    val location: String?,
    val number: String?,
    val openingInfo: String?,
)

fun PlaceDetailDto.toPlaceDetail(): PlaceDetailInfo {
    return PlaceDetailInfo(
        id = id,
        name = name,
        likeCount = likeCount ?: 0,
        reviewCount = reviewCount ?: 0,
        filter = filter,
        tags = tags ?: "",
        image = image ?: "",
        location = location ?: "",
        number = number ?: "",
        openingInfo = openingInfo ?: ""
    )
}
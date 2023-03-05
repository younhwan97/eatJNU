package kr.co.younhwan.eatjnu.data.remote.dto

import kr.co.younhwan.eatjnu.domain.model.FoodImageInfo
import kr.co.younhwan.eatjnu.domain.model.PlaceDetailInfo
import kr.co.younhwan.eatjnu.domain.model.ReviewInfo

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
    val images: List<FoodImageInfo>?,
    val lat: Double?,
    val lon: Double?,
    val reviews: List<ReviewInfo>?
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
        images = images ?: emptyList(),
        location = location ?: "",
        number = number,
        openingInfo = openingInfo,
        lat = lat ?: 0.0,
        lon = lon ?: 0.0,
        reviews = reviews ?: emptyList()
    )
}
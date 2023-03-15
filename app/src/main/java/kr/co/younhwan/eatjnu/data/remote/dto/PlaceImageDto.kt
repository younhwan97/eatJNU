package kr.co.younhwan.eatjnu.data.remote.dto

import kr.co.younhwan.eatjnu.domain.model.PlaceImage

data class PlaceImageDto(
    val url: String?
)

fun PlaceImageDto.toPlaceImage(): PlaceImage {
    return PlaceImage(
        url = url ?: ""
    )
}
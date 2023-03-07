package kr.co.younhwan.eatjnu.data.remote.dto

import kr.co.younhwan.eatjnu.domain.model.FoodImageInfo

data class FoodImageDto(
    val url: String?
)

fun FoodImageDto.toFoodImage(): FoodImageInfo {
    return FoodImageInfo(
        url = url ?: ""
    )
}
package kr.co.younhwan.eatjnu.data.remote.dto

import kr.co.younhwan.eatjnu.domain.model.FoodImage

data class FoodImageDto(
    val url: String?
)

fun FoodImageDto.toFoodImage(): FoodImage {
    return FoodImage(
        url = url ?: ""
    )
}
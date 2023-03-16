package kr.co.younhwan.eatjnu.data.remote.dto

data class LikePlaceListDto(
    val items: List<LikePlaceDto>?
)

data class LikePlaceDto(
    val placeId: Int?
)

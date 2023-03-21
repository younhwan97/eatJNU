package kr.co.younhwan.eatjnu.data.remote.dto

data class LikePlaceIdListDto(
    val items: List<LikePlaceIdDto>?
)

data class LikePlaceIdDto(
    val placeId: Int?
)

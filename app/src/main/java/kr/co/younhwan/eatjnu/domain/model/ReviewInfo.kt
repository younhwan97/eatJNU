package kr.co.younhwan.eatjnu.domain.model

data class ReviewInfo(
    val comment: String? = null,
    val writingTime: String? = null,
    val userId: String? = null,
    val likeCount: Int? = 0,
    val placeId: String? = null
)

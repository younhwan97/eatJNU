package kr.co.younhwan.eatjnu.domain.model

data class ReviewInfo(
    val comment: String,
    val writingTime: String,
    val userId: String,
    val likeCount: Int,
    val placeId: String
)

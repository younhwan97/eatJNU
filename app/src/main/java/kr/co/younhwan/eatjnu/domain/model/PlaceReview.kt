package kr.co.younhwan.eatjnu.domain.model

data class PlaceReview(
    val reviewId: Int,
    val comment: String,
    val writingTime: String,
    val userId: String,
    val likeCount: Int,
    val placeId: String
)

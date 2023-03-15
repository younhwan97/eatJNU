package kr.co.younhwan.eatjnu.data.remote.dto

import kr.co.younhwan.eatjnu.domain.model.PlaceReview

data class PlaceReviewDto(
    val reviewId: Int?,
    val comment: String?,
    val writingTime: String?,
    val userId: String?,
    val likeCount: Int?,
    val placeId: String?
)

fun PlaceReviewDto.toReview(): PlaceReview {
    return PlaceReview(
        reviewId = reviewId ?: -1,
        comment = comment ?: "",
        writingTime = writingTime ?: "",
        userId = userId ?: "",
        likeCount = likeCount ?: 0,
        placeId = placeId ?: ""
    )
}
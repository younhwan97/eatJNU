package kr.co.younhwan.eatjnu.data.remote.dto

import kr.co.younhwan.eatjnu.domain.model.Review

data class ReviewDto(
    val comment: String?,
    val writingTime: String?,
    val userId: String?,
    val likeCount: Int?,
    val placeId: String?
)

fun ReviewDto.toReview(): Review {
    return Review(
        comment = comment ?: "",
        writingTime = writingTime ?: "",
        userId = userId ?: "",
        likeCount = likeCount ?: 0,
        placeId = placeId ?: ""
    )
}
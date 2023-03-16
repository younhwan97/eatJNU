package kr.co.younhwan.eatjnu.data.remote.dto

import kr.co.younhwan.eatjnu.domain.model.PlaceReviewReport

data class PlaceReviewReportListDto(
    val items: List<PlaceReviewReportDto>?
)

data class PlaceReviewReportDto(
    val reviewId: Int?
)

fun PlaceReviewReportDto.toPlaceReviewReport(): PlaceReviewReport {
    return PlaceReviewReport(
        reviewId = reviewId ?: -1
    )
}
package kr.co.younhwan.eatjnu.data.repository

import kr.co.younhwan.eatjnu.data.remote.EatJnuApi
import kr.co.younhwan.eatjnu.data.remote.dto.*
import kr.co.younhwan.eatjnu.domain.model.PlaceReview
import kr.co.younhwan.eatjnu.domain.repository.EatJnuRepository
import javax.inject.Inject

class EatJnuRepositoryImpl @Inject constructor(
    private val api: EatJnuApi
) : EatJnuRepository {

    override suspend fun getPlaceList(areaType: String): List<PlaceSummaryDto> {
        return api.getPlaceList(areaType = areaType).items ?: emptyList()
    }

    override suspend fun getPlaceDetail(placeId: String): PlaceDetailDto {
        return api.getPlaceDetail(placeId = placeId)
    }

    override suspend fun getLikePlaceList(userId: String): List<LikePlaceDto> {
        return api.getLikePlaceList(userId = userId).items ?: emptyList()
    }

    override suspend fun addLikePlace(userId: String, placeId: String): Boolean {
        return api.addLikePlace(userId = userId, placeId = placeId).isSuccess ?: false
    }

    override suspend fun removeLikePlace(userId: String, placeId: String): Boolean {
        return api.removeLikePlace(userId = userId, placeId = placeId).isSuccess ?: false
    }

    override suspend fun createPlaceReview(userId: String, placeId: String, comment: String): Boolean {
        return api.createPlaceReview(
            placeReview = PlaceReview(
                reviewId = -1,
                comment = comment,
                placeId = placeId,
                userId = userId,
                writingTime = "",
                likeCount = 0
            )
        ).isSuccess ?: false
    }

    override suspend fun getPlaceReviewReport(userId: String): List<PlaceReviewReportDto> {
        return api.getPlaceReviewReport(
            userId = userId
        ).items ?: emptyList()
    }

    override suspend fun addPlaceReviewReport(userId: String, reviewId: String): Boolean {
        return api.addPlaceReviewReport(
            userId = userId,
            reviewId = reviewId
        ).isSuccess ?: false
    }
}
package kr.co.younhwan.eatjnu.data.repository

import kr.co.younhwan.eatjnu.data.remote.EatJnuApi
import kr.co.younhwan.eatjnu.data.remote.dto.*
import kr.co.younhwan.eatjnu.domain.model.PlaceReview
import kr.co.younhwan.eatjnu.domain.repository.EatJnuRepository
import javax.inject.Inject

class EatJnuRepositoryImpl @Inject constructor(
    private val api: EatJnuApi
) : EatJnuRepository {

    // 1. 장소 목록을 가져오는 API
    override suspend fun getPlaceList(areaType: String): List<PlaceSummaryDto> {
        return api.getPlaceList(areaType = areaType).items ?: emptyList()
    }

    // 2. 장소 세부 정보를 가져오는 API
    override suspend fun getPlaceDetail(placeId: String): PlaceDetailDto {
        return api.getPlaceDetail(placeId = placeId)
    }

    // 3. 장소의 '좋아요' 기능과 관련된 API
    override suspend fun getLikePlaceIdList(userId: String): List<LikePlaceIdDto> {
        return api.getLikePlaceIdList(userId = userId).items ?: emptyList()
    }

    override suspend fun getLikePlaceList(userId: String): List<PlaceSummaryDto> {
        return api.getLikePlaceList(userId = userId).items ?: emptyList()
    }

    override suspend fun addLikePlace(userId: String, placeId: String): Boolean {
        return api.addLikePlace(userId = userId, placeId = placeId).isSuccess ?: false
    }

    override suspend fun removeLikePlace(userId: String, placeId: String): Boolean {
        return api.removeLikePlace(userId = userId, placeId = placeId).isSuccess ?: false
    }

    // 4. 장소의 '리뷰' 기능과 관련된 API
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

    override suspend fun getPlaceReviewReportList(userId: String): List<PlaceReviewReportDto> {
        return api.getPlaceReviewReportList(
            userId = userId
        ).items ?: emptyList()
    }

    override suspend fun addPlaceReviewReport(userId: String, reviewId: String): Boolean {
        return api.addPlaceReviewReport(
            userId = userId,
            reviewId = reviewId
        ).isSuccess ?: false
    }

    override suspend fun getUgcValue(userId: String): Boolean {
        return api.getUgcValue(userId).isSuccess ?: false
    }

    override suspend fun addUgcValue(userId: String): Boolean {
        return api.addUgcValue(userId).isSuccess ?: false
    }
}
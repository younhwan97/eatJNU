package kr.co.younhwan.eatjnu.domain.repository

import kr.co.younhwan.eatjnu.data.remote.dto.LikePlaceDto
import kr.co.younhwan.eatjnu.data.remote.dto.PlaceDetailDto
import kr.co.younhwan.eatjnu.data.remote.dto.PlaceSummaryDto
import kr.co.younhwan.eatjnu.data.remote.dto.PlaceReviewReportDto

interface EatJnuRepository {

    suspend fun getPlaceList(areaType: String): List<PlaceSummaryDto>

    suspend fun getPlaceDetail(placeId: String): PlaceDetailDto

    suspend fun getLikePlaceList(userId: String): List<LikePlaceDto>

    suspend fun addLikePlace(userId: String, placeId: String): LikePlaceDto

    suspend fun removeLikePlace(userId: String, placeId: String): LikePlaceDto

    suspend fun createPlaceReview(userId: String, placeId: String, comment: String): Boolean

    suspend fun getPlaceReviewReport(userId: String): List<PlaceReviewReportDto>

    suspend fun addPlaceReviewReport(userId: String, reviewId: String): Boolean
}
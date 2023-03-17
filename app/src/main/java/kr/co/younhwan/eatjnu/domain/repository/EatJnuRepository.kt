package kr.co.younhwan.eatjnu.domain.repository

import kr.co.younhwan.eatjnu.data.remote.dto.*

interface EatJnuRepository {

    suspend fun getPlaceList(areaType: String): List<PlaceSummaryDto>

    suspend fun getPlaceDetail(placeId: String): PlaceDetailDto

    suspend fun getLikePlaceList(userId: String): List<LikePlaceDto>

    suspend fun addLikePlace(userId: String, placeId: String): Boolean

    suspend fun removeLikePlace(userId: String, placeId: String): Boolean

    suspend fun createPlaceReview(userId: String, placeId: String, comment: String): Boolean

    suspend fun getPlaceReviewReportList(userId: String): List<PlaceReviewReportDto>

    suspend fun addPlaceReviewReport(userId: String, reviewId: String): Boolean

    suspend fun getUgcValue(userId: String): Boolean

    suspend fun addUgcValue(userId: String): Boolean
}
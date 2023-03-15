package kr.co.younhwan.eatjnu.data.repository

import kr.co.younhwan.eatjnu.data.remote.EatJnuApi
import kr.co.younhwan.eatjnu.data.remote.dto.LikePlaceDto
import kr.co.younhwan.eatjnu.data.remote.dto.PlaceDetailDto
import kr.co.younhwan.eatjnu.data.remote.dto.PlaceDto
import kr.co.younhwan.eatjnu.domain.model.Review
import kr.co.younhwan.eatjnu.domain.repository.EatJnuRepository
import javax.inject.Inject

class EatJnuRepositoryImpl @Inject constructor(
    private val api: EatJnuApi
) : EatJnuRepository {

    override suspend fun getPlaceList(areaType: String): List<PlaceDto> {
        return api.getPlaceList(areaType = areaType).items
    }

    override suspend fun getPlaceDetail(placeId: String): PlaceDetailDto {
        return api.getPlaceDetail(placeId = placeId)
    }

    override suspend fun getLikePlaceList(userId: String): List<LikePlaceDto> {
        return api.getLikePlaceList(userId = userId).items ?: emptyList()
    }

    override suspend fun addLikePlace(userId: String, placeId: String): LikePlaceDto {
        return api.addLikePlace(userId = userId, placeId = placeId)
    }

    override suspend fun removeLikePlace(userId: String, placeId: String): LikePlaceDto {
        return api.removeLikePlace(userId = userId, placeId = placeId)
    }

    override suspend fun createPlaceReview(userId: String, placeId: String, comment: String): Boolean {
        return api.createPlaceReview(
            review = Review(
                reviewId = -1,
                comment = comment,
                placeId = placeId,
                userId = userId,
                writingTime = "",
                likeCount = 0
            )
        ).isSuccess
    }

    override suspend fun reportPlaceReview(userId: String, reviewId:String): Boolean {
        return api.reportPlaceReview(
            userId = userId,
            reviewId = reviewId
        ).isSuccess
    }
}
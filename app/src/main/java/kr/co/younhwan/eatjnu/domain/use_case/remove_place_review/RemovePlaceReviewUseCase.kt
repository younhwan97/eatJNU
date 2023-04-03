package kr.co.younhwan.eatjnu.domain.use_case.remove_place_review

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kr.co.younhwan.eatjnu.common.Resource
import kr.co.younhwan.eatjnu.domain.repository.EatJnuRepository
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class RemovePlaceReviewUseCase @Inject constructor(
    private val repository: EatJnuRepository
) {
    operator fun invoke(reviewId: String, userId: String, placeId: String): Flow<Resource<Boolean>> = flow {
        try {
            emit(Resource.Loading())
            val isSuccess = repository.removePlaceReview(reviewId = reviewId, userId = userId, placeId = placeId)
            emit(Resource.Success(data = isSuccess))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An expected error!"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server"))
        }
    }
}
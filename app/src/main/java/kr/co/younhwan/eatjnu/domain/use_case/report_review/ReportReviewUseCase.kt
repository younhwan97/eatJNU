package kr.co.younhwan.eatjnu.domain.use_case.report_review

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kr.co.younhwan.eatjnu.common.Resource
import kr.co.younhwan.eatjnu.domain.repository.EatJnuRepository
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ReportReviewUseCase @Inject constructor(
    private val repository: EatJnuRepository
) {
    operator fun invoke(userId: String, reviewId: String): Flow<Resource<Boolean>> = flow {
        try {
            emit(Resource.Loading())
            val isSuccess = repository.reportPlaceReview(userId = userId, reviewId = reviewId)
            emit(Resource.Success(data = isSuccess))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An expected error!"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server"))
        }
    }
}
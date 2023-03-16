package kr.co.younhwan.eatjnu.domain.use_case.get_place_review_report_list

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kr.co.younhwan.eatjnu.common.Resource
import kr.co.younhwan.eatjnu.data.remote.dto.toPlaceReviewReport
import kr.co.younhwan.eatjnu.domain.model.PlaceReviewReport
import kr.co.younhwan.eatjnu.domain.repository.EatJnuRepository
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetPlaceReviewReportListUseCase @Inject constructor(
    private val repository: EatJnuRepository
) {
    operator fun invoke(userId: String): Flow<Resource<List<PlaceReviewReport>>> = flow {
        try {
            emit(Resource.Loading())
            val data = repository.getPlaceReviewReportList(userId = userId).map { it.toPlaceReviewReport() }
            emit(Resource.Success(data = data))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An expected error!"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server"))
        }
    }
}
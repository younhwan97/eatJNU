package kr.co.younhwan.eatjnu.domain.use_case.get_place_detail

import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.Flow
import kr.co.younhwan.eatjnu.common.Resource
import kr.co.younhwan.eatjnu.data.remote.dto.toPlaceDetail
import kr.co.younhwan.eatjnu.domain.model.PlaceDetail
import kr.co.younhwan.eatjnu.domain.repository.EatJnuRepository
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetPlaceDetailUseCase @Inject constructor(
    private val repository: EatJnuRepository
) {
    operator fun invoke(placeId: String): Flow<Resource<PlaceDetail>> = flow {
        try {
            emit(Resource.Loading())
            val placeDetail = repository.getPlaceDetail(placeId = placeId).toPlaceDetail()
            emit(Resource.Success(data = placeDetail))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An expected error!"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server"))
        }
    }
}
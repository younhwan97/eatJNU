package kr.co.younhwan.eatjnu.domain.use_case.get_like_place_id_list

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kr.co.younhwan.eatjnu.common.Resource
import kr.co.younhwan.eatjnu.domain.repository.EatJnuRepository
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetLikePlaceIdListUseCase @Inject constructor(
    private val repository: EatJnuRepository
) {
    operator fun invoke(userId: String): Flow<Resource<List<Int>>> = flow {
        try {
            emit(Resource.Loading())
            val likePlaceIds = repository.getLikePlaceIdList(userId = userId).map { it.placeId ?: -1 }
            emit(Resource.Success(data = likePlaceIds))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An expected error!"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server"))
        }
    }
}
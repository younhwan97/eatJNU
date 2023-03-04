package kr.co.younhwan.eatjnu.domain.use_case.get_like_place_list

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kr.co.younhwan.eatjnu.common.Resource
import kr.co.younhwan.eatjnu.domain.repository.EatJnuRepository
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetLikePlaceListUseCase @Inject constructor(
    private val repository: EatJnuRepository
) {

    operator fun invoke(userId: String): Flow<Resource<List<Int>>> = flow {
        try {
            emit(Resource.Loading())
            val likePlaces = repository.getLikePlaceList(userId = userId).map { it.placeId }
            Log.d("temp", likePlaces.toString())
            emit(Resource.Success(data = likePlaces))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An expected error!"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server"))
        }
    }
}
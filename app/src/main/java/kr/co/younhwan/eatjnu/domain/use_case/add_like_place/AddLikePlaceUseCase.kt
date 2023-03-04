package kr.co.younhwan.eatjnu.domain.use_case.add_like_place

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kr.co.younhwan.eatjnu.common.Resource
import kr.co.younhwan.eatjnu.data.remote.dto.LikePlaceDto
import kr.co.younhwan.eatjnu.domain.repository.EatJnuRepository
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class AddLikePlaceUseCase @Inject constructor(
    private val repository: EatJnuRepository
) {

    operator fun invoke(userId: String, placeId: String): Flow<Resource<LikePlaceDto>> = flow {

        try {
            emit(Resource.Loading())
            val likePlace = repository.addLikePlace(userId = userId, placeId = placeId)
            emit(Resource.Success(data = likePlace))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An expected error!"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server"))
        }
    }
}
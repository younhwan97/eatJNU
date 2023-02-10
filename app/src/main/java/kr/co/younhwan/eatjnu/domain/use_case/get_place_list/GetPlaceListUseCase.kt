package kr.co.younhwan.eatjnu.domain.use_case.get_place_list

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kr.co.younhwan.eatjnu.common.Resource
import kr.co.younhwan.eatjnu.data.remote.dto.toPlaceInfo
import kr.co.younhwan.eatjnu.domain.model.PlaceInfo
import kr.co.younhwan.eatjnu.domain.repository.EatJnuRepository
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetPlaceListUseCase @Inject constructor(
    private val repository: EatJnuRepository
) {

    operator fun invoke(areaType: String): Flow<Resource<List<PlaceInfo>>> = flow {
        try {
            emit(Resource.Loading())
            val places = repository.getPlaceList(areaType = areaType).map { it.toPlaceInfo() }
            emit(Resource.Success(data = places))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An expected error!"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server"))
        }
    }
}
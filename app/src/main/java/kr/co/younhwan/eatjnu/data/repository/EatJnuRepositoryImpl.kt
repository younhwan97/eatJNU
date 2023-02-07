package kr.co.younhwan.eatjnu.data.repository

import android.util.Log
import kr.co.younhwan.eatjnu.data.remote.EatJnuApi
import kr.co.younhwan.eatjnu.data.remote.dto.PlaceInfo
import kr.co.younhwan.eatjnu.domain.repository.EatJnuRepository
import javax.inject.Inject

class EatJnuRepositoryImpl @Inject constructor(
    private val api: EatJnuApi
) : EatJnuRepository {

    override suspend fun getPlaceList(areaType: String): List<PlaceInfo> {
        return api.getPlaceList(areaType = areaType).items
    }
}
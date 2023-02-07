package kr.co.younhwan.eatjnu.domain.repository

import kr.co.younhwan.eatjnu.data.remote.dto.PlaceInfo

interface EatJnuRepository {

    suspend fun getPlaceList(areaType: String): List<PlaceInfo>


}
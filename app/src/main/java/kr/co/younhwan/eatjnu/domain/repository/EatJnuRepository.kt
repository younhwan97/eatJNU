package kr.co.younhwan.eatjnu.domain.repository

import kr.co.younhwan.eatjnu.data.remote.dto.PlaceDto

interface EatJnuRepository {

    suspend fun getPlaceList(areaType: String): List<PlaceDto>
}
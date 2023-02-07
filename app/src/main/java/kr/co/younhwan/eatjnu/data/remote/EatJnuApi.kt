package kr.co.younhwan.eatjnu.data.remote

import kr.co.younhwan.eatjnu.data.remote.dto.PlaceListDto
import retrofit2.http.GET

interface EatJnuApi {
    @GET("PlaceList/")
    suspend fun getPlaceList(): PlaceListDto
}
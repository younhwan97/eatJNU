package kr.co.younhwan.eatjnu.data.remote

import kr.co.younhwan.eatjnu.data.remote.dto.PlaceListDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface EatJnuApi {

    @GET("PlaceList/{areaType}")
    suspend fun getPlaceList(@Path("areaType") areaType: String): PlaceListDto
}
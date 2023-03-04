package kr.co.younhwan.eatjnu.data.remote

import kr.co.younhwan.eatjnu.data.remote.dto.LikePlaceDto
import kr.co.younhwan.eatjnu.data.remote.dto.LikePlaceListDto
import kr.co.younhwan.eatjnu.data.remote.dto.PlaceDetailDto
import kr.co.younhwan.eatjnu.data.remote.dto.PlaceListDto
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface EatJnuApi {

    @GET("PlaceList/{areaType}")
    suspend fun getPlaceList(@Path("areaType") areaType: String): PlaceListDto

    @GET("PlaceDetail/{placeId}")
    suspend fun getPlaceDetail(@Path("placeId") placeId: String): PlaceDetailDto

    @GET("LikePlace/{userId}")
    suspend fun getLikePlaceList(@Path("userId") userId: String): LikePlaceListDto

    @PUT("LikePlace/{userId}/{placeId}")
    suspend fun addLikePlace(@Path("userId") userId: String, @Path("placeId") placeId: String): LikePlaceDto

    @DELETE("LikePlace/{userId}/{placeId}")
    suspend fun removeLikePlace(@Path("userId") userId: String, @Path("placeId") placeId: String): LikePlaceDto
}
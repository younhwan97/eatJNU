package kr.co.younhwan.eatjnu.data.remote

import kr.co.younhwan.eatjnu.data.remote.dto.*
import kr.co.younhwan.eatjnu.domain.model.Review
import retrofit2.http.*

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

    @Headers("Content-Type: application/json")
    @POST("PlaceReview/")
    suspend fun createPlaceReview(@Body review: Review): ResponseDto
}
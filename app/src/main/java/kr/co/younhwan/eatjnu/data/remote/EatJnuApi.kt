package kr.co.younhwan.eatjnu.data.remote

import kr.co.younhwan.eatjnu.data.remote.dto.*
import kr.co.younhwan.eatjnu.domain.model.PlaceReview
import retrofit2.http.*

interface EatJnuApi {

    @GET("PlaceList/{areaType}")
    suspend fun getPlaceList(@Path("areaType") areaType: String): PlaceSummaryListDto

    @GET("PlaceDetail/{placeId}")
    suspend fun getPlaceDetail(@Path("placeId") placeId: String): PlaceDetailDto

    @GET("LikePlace/{userId}")
    suspend fun getLikePlaceList(@Path("userId") userId: String): LikePlaceListDto

    @PUT("LikePlace/{userId}/{placeId}")
    suspend fun addLikePlace(@Path("userId") userId: String, @Path("placeId") placeId: String): CommonResponseDto

    @DELETE("LikePlace/{userId}/{placeId}")
    suspend fun removeLikePlace(@Path("userId") userId: String, @Path("placeId") placeId: String): CommonResponseDto

    @Headers("Content-Type: application/json")
    @POST("PlaceReview/")
    suspend fun createPlaceReview(@Body placeReview: PlaceReview): CommonResponseDto

    @GET("PlaceReview/report/{userId}")
    suspend fun getPlaceReviewReport(@Path("userId") userId: String) : PlaceReviewReportListDto

    @PUT("PlaceReview/report/{userId}/{reviewId}")
    suspend fun addPlaceReviewReport(@Path("userId") userId: String, @Path("reviewId") reviewId: String): CommonResponseDto
}
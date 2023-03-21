package kr.co.younhwan.eatjnu.data.remote

import kr.co.younhwan.eatjnu.data.remote.dto.*
import kr.co.younhwan.eatjnu.domain.model.PlaceReview
import retrofit2.http.*

interface EatJnuApi {

    // 1. 장소 목록을 가져오는 API
    @GET("PlaceList/{areaType}")
    suspend fun getPlaceList(@Path("areaType") areaType: String): PlaceSummaryListDto

    // 2. 장소 세부 정보를 가져오는 API
    @GET("PlaceDetail/{placeId}")
    suspend fun getPlaceDetail(@Path("placeId") placeId: String): PlaceDetailDto

    // 3. 장소의 '좋아요' 기능과 관련된 API
    @GET("LikePlace/{userId}")
    suspend fun getLikePlaceIdList(@Path("userId") userId: String): LikePlaceIdListDto

    @GET("LikePlace/detail/{userId}")
    suspend fun getLikePlaceList(@Path("userId") userId: String): PlaceSummaryListDto

    @PUT("LikePlace/{userId}/{placeId}")
    suspend fun addLikePlace(@Path("userId") userId: String, @Path("placeId") placeId: String): CommonResponseDto

    @DELETE("LikePlace/{userId}/{placeId}")
    suspend fun removeLikePlace(@Path("userId") userId: String, @Path("placeId") placeId: String): CommonResponseDto

    // 4. 장소의 '리뷰' 기능과 관련된 API
    @Headers("Content-Type: application/json")
    @POST("PlaceReview/")
    suspend fun createPlaceReview(@Body placeReview: PlaceReview): CommonResponseDto

    @GET("PlaceReview/report/{userId}")
    suspend fun getPlaceReviewReportList(@Path("userId") userId: String): PlaceReviewReportListDto

    @PUT("PlaceReview/report/{userId}/{reviewId}")
    suspend fun addPlaceReviewReport(@Path("userId") userId: String, @Path("reviewId") reviewId: String): CommonResponseDto

    // 5. 유저의 'UGC' 값과 관련된 API
    @GET("Ugc/{userId}")
    suspend fun getUgcValue(@Path("userId") userId: String): CommonResponseDto

    @PUT("Ugc/{userId}")
    suspend fun addUgcValue(@Path("userId") userId: String): CommonResponseDto
}
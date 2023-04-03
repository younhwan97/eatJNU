package kr.co.younhwan.eatjnu.presentation.place_detail

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kr.co.younhwan.eatjnu.common.Constants
import kr.co.younhwan.eatjnu.common.Resource
import kr.co.younhwan.eatjnu.domain.model.PlaceDetail
import kr.co.younhwan.eatjnu.domain.model.PlaceReview
import kr.co.younhwan.eatjnu.domain.model.PlaceReviewReport
import kr.co.younhwan.eatjnu.domain.use_case.add_like_place.AddLikePlaceUseCase
import kr.co.younhwan.eatjnu.domain.use_case.create_place_review.CreatePlaceReviewUseCase
import kr.co.younhwan.eatjnu.domain.use_case.get_like_place_id_list.GetLikePlaceIdListUseCase
import kr.co.younhwan.eatjnu.domain.use_case.get_place_detail.GetPlaceDetailUseCase
import kr.co.younhwan.eatjnu.domain.use_case.remove_like_place.RemoveLikePlaceUseCase
import kr.co.younhwan.eatjnu.domain.use_case.add_place_review_report.AddPlaceReviewReport
import kr.co.younhwan.eatjnu.domain.use_case.add_ugc_value.AddUgcValueUseCase
import kr.co.younhwan.eatjnu.domain.use_case.get_place_review_report_list.GetPlaceReviewReportListUseCase
import kr.co.younhwan.eatjnu.domain.use_case.get_ugc_value.GetUgcValueUseCase
import kr.co.younhwan.eatjnu.domain.use_case.remove_place_review.RemovePlaceReviewUseCase
import javax.inject.Inject

@HiltViewModel
class PlaceDetailViewModel @Inject constructor(
    private val getPlaceDetailUseCase: GetPlaceDetailUseCase,
    private val addLikePlaceUseCase: AddLikePlaceUseCase,
    private val getLikePlaceIdListUseCase: GetLikePlaceIdListUseCase,
    private val removeLikePlaceUseCase: RemoveLikePlaceUseCase,
    private val createPlaceReviewUseCase: CreatePlaceReviewUseCase,
    private val removePlaceReviewUseCase: RemovePlaceReviewUseCase,
    private val addPlaceReviewReport: AddPlaceReviewReport,
    private val getPlaceReviewReportListUseCase: GetPlaceReviewReportListUseCase,
    private val getUgcValueUseCase: GetUgcValueUseCase,
    private val addUgcValueUseCase: AddUgcValueUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    var error = mutableStateOf("")
    var isLoading = mutableStateOf(true)

    val userId = mutableStateOf("")
    val isLikePlace = mutableStateOf(false)
    val isUgcAgree = mutableStateOf(false)
    val reportReviews = mutableListOf<PlaceReviewReport>()
    var placeDetail = mutableStateOf(
        PlaceDetail(
            -1, "", 0, 0, "", "", "", "", 0.0, 0.0, "", "", emptyList(), emptyList()
        )
    )

    init {
        viewModelScope.launch {
            // 1. 유저 아이디 값 초기화
            userId.value = savedStateHandle.get<String>(Constants.PARAM_USER_ID) ?: ""
            // 2. 장소 아이디 값 초기화
            val placeId = savedStateHandle.get<String>(Constants.PARAM_PLACE_ID) ?: "1"
            // 3. 유저가 신고한 리뷰 목록 초기화
            getPlaceReviewReportList(userId = userId.value)
            // 4. like place 체크
            checkLikePlace(userId = userId.value, placeId = placeId)
            // 5. Ugc 동의 여부 확인
            checkUgcAgree(userId = userId.value)
            // 6. 장소 세부 정보 초기화
            getPlaceDetail(placeId = placeId)
        }
    }

    // (유저 ID를 이용해) 유저가 신고한 리뷰 목록을 가져오는 함수
    private fun getPlaceReviewReportList(userId: String) {
        getPlaceReviewReportListUseCase(userId = userId).onEach { result ->
            when (result) {
                is Resource.Loading -> Unit
                is Resource.Error -> Unit
                is Resource.Success -> reportReviews.addAll(result.data ?: emptyList())
            }
        }.launchIn(viewModelScope)
    }

    // (유저 ID, 장소 ID를 이용해) 해당 유저가 해당 장소에 '좋아요'를 눌렀는지 확인하는 함수
    private fun checkLikePlace(userId: String, placeId: String) {
        getLikePlaceIdListUseCase(userId = userId).onEach { result ->
            when (result) {
                is Resource.Loading -> Unit
                is Resource.Error -> Unit
                is Resource.Success -> isLikePlace.value = result.data?.contains(placeId.toInt()) ?: false
            }
        }.launchIn(viewModelScope)
    }

    // (유저 ID를 이용해) 해당 유저가 UGC 약관에 동의를 했는지 확인하는 함수
    private fun checkUgcAgree(userId: String) {
        getUgcValueUseCase(userId = userId).onEach { result ->
            when (result) {
                is Resource.Loading -> Unit
                is Resource.Error -> Unit
                is Resource.Success -> isUgcAgree.value = result.data ?: false
            }
        }.launchIn(viewModelScope)
    }

    // 장소 세부 정보를 초기화하는 함수
    private fun getPlaceDetail(placeId: String) {
        getPlaceDetailUseCase(placeId = placeId).onEach { result ->
            when (result) {
                is Resource.Loading -> Unit
                is Resource.Error -> {
                    isLoading.value = false
                    error.value = result.message ?: "error"
                }
                is Resource.Success -> {
                    isLoading.value = false
                    placeDetail.value = result.data ?: placeDetail.value
                }
            }
        }.launchIn(viewModelScope)
    }

    // 장소를 유저의 '좋아요' 목록에 추가하는 함수
    fun addLikePlace(placeId: Int) {
        addLikePlaceUseCase(userId = userId.value, placeId = placeId.toString()).onEach { result ->
            when (result) {
                is Resource.Loading -> Unit
                is Resource.Error -> Unit
                is Resource.Success -> {
                    if (result.data == true) {
                        isLikePlace.value = true
                        placeDetail.value = placeDetail.value.copy(likeCount = placeDetail.value.likeCount.plus(1))
                    }
                }
            }
        }.launchIn(viewModelScope)
    }

    // 장소를 유저의 '좋아요' 목록에서 제거하는 함수
    fun removeLikePlace(placeId: Int) {
        removeLikePlaceUseCase(userId = userId.value, placeId = placeId.toString()).onEach { result ->
            when (result) {
                is Resource.Loading -> Unit
                is Resource.Error -> Unit
                is Resource.Success -> {
                    if (result.data == true) {
                        isLikePlace.value = false
                        placeDetail.value = placeDetail.value.copy(likeCount = placeDetail.value.likeCount.minus(1))
                    }
                }
            }
        }.launchIn(viewModelScope)
    }

    // 장소에 리뷰를 추가하는 함수
    fun createPlaceReview(userId: String, placeId: Int, comment: String) {
        createPlaceReviewUseCase(userId = userId, placeId = placeId, comment = comment).onEach { result ->
            when (result) {
                is Resource.Loading -> Unit
                is Resource.Error -> Unit
                is Resource.Success -> {
                    if (result.data == true) { // 리뷰가 추가 됐을 때
                        // (제일 앞에) 가짜 데이터 추가
                        placeDetail.value = placeDetail.value.copy(
                            placeReviews = listOf(
                                PlaceReview(
                                    reviewId = -1,
                                    placeId = placeId,
                                    comment = comment,
                                    writingTime = "지금",
                                    likeCount = 0,
                                    userId = userId
                                )
                            ) + placeDetail.value.placeReviews
                        )
                    }
                }
            }
        }.launchIn(viewModelScope)
    }

    // 장소에 리뷰를 삭제하는 함수
    fun removePlaceReview(reviewId: Int, userId: String, placeId: Int) {
        removePlaceReviewUseCase(reviewId = reviewId.toString(), userId = userId, placeId = placeId.toString()).onEach { result ->
            when (result) {
                is Resource.Loading -> Unit
                is Resource.Error -> Unit
                is Resource.Success -> {
                    if (result.data == true) {
                        // 리뷰 삭제
                        val newPlaceReviews = mutableListOf<PlaceReview>()

                        for (review in placeDetail.value.placeReviews) {
                            if (review.reviewId != reviewId) {
                                newPlaceReviews.add(review)
                            }
                        }

                        placeDetail.value = placeDetail.value.copy(
                            placeReviews = newPlaceReviews
                        )
                    }
                }
            }
        }.launchIn(viewModelScope)
    }

    fun addPlaceReviewReport(userId: String, reviewId: Int) {
        addPlaceReviewReport(userId = userId, reviewId = reviewId.toString()).onEach { result ->
            when (result) {
                is Resource.Loading -> Unit
                is Resource.Error -> Unit
                is Resource.Success -> {
                    if (result.data == true) {
                        val newPlaceReviews = mutableListOf<PlaceReview>()

                        for (review in placeDetail.value.placeReviews) {
                            if (review.reviewId != reviewId) {
                                newPlaceReviews.add(review)
                            }
                        }

                        placeDetail.value = placeDetail.value.copy(
                            placeReviews = newPlaceReviews
                        )
                    }
                }
            }
        }.launchIn(viewModelScope)
    }

    fun addUgcValue() {
        addUgcValueUseCase(userId = userId.value).onEach { result ->
            when (result) {
                is Resource.Loading -> Unit
                is Resource.Error -> Unit
                is Resource.Success -> {
                    if (result.data == true) {
                        isUgcAgree.value = true
                    }
                }
            }
        }.launchIn(viewModelScope)
    }
}
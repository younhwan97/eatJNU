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
import kr.co.younhwan.eatjnu.domain.model.Review
import kr.co.younhwan.eatjnu.domain.use_case.add_like_place.AddLikePlaceUseCase
import kr.co.younhwan.eatjnu.domain.use_case.create_review.CreateReviewUseCase
import kr.co.younhwan.eatjnu.domain.use_case.get_like_place_list.GetLikePlaceListUseCase
import kr.co.younhwan.eatjnu.domain.use_case.get_place_detail.GetPlaceDetailUseCase
import kr.co.younhwan.eatjnu.domain.use_case.remove_like_place.RemoveLikePlaceUseCase
import javax.inject.Inject

@HiltViewModel
class PlaceDetailViewModel @Inject constructor(
    private val getPlaceDetailUseCase: GetPlaceDetailUseCase,
    private val addLikePlaceUseCase: AddLikePlaceUseCase,
    private val getLikePlaceListUseCase: GetLikePlaceListUseCase,
    private val removeLikePlaceUseCase: RemoveLikePlaceUseCase,
    private val createReviewUseCase: CreateReviewUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    var error = mutableStateOf("")
    var isLoading = mutableStateOf(true)

    val userId = mutableStateOf("")
    val isLikePlace = mutableStateOf(false)
    val placeDetail = mutableStateOf(
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
            // 3. 장소 세부 정보 초기화
            getPlaceDetail(placeId = placeId)
            // 4. like place 체크
            checkLikePlace(userId = userId.value, placeId = placeId)
        }
    }

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

    // 여기까지

    private fun checkLikePlace(userId: String, placeId: String) {
        getLikePlaceListUseCase(userId = userId).onEach { result ->
            when (result) {
                is Resource.Loading -> Unit
                is Resource.Error -> Unit
                is Resource.Success -> isLikePlace.value = result.data?.contains(placeId.toInt()) ?: false
            }
        }.launchIn(viewModelScope)
    }

    fun addLikePlace(placeId: Int) {
        addLikePlaceUseCase(userId = userId.value, placeId = placeId.toString()).onEach { result ->
            when (result) {
                is Resource.Loading -> Unit
                is Resource.Error -> Unit
                is Resource.Success -> {
                    isLikePlace.value = true
                    placeDetail.value = placeDetail.value.copy(likeCount = placeDetail.value.likeCount.plus(1))
                }
            }
        }.launchIn(viewModelScope)
    }

    fun removeLikePlace(placeId: Int) {
        removeLikePlaceUseCase(userId = userId.value, placeId = placeId.toString()).onEach { result ->
            when (result) {
                is Resource.Loading -> Unit
                is Resource.Error -> Unit
                is Resource.Success -> {
                    isLikePlace.value = false
                    placeDetail.value = placeDetail.value.copy(likeCount = placeDetail.value.likeCount.minus(1))
                }
            }
        }.launchIn(viewModelScope)
    }

    fun createPlaceReview(userId: String, placeId: String, comment: String) {
        createReviewUseCase(userId = userId, placeId = placeId, comment = comment).onEach { result ->
            when (result) {
                is Resource.Loading -> Unit
                is Resource.Error -> Unit
                is Resource.Success -> {
                    if (result.data == true) { // 리뷰가 추가 됐을 때
                        // (제일 앞에) 가짜 데이터 추가
                        placeDetail.value = placeDetail.value.copy(
                            reviews = listOf(
                                Review(
                                    placeId = placeId,
                                    comment = comment,
                                    writingTime = "지금",
                                    likeCount = 0,
                                    userId = userId
                                )
                            ) + placeDetail.value.reviews
                        )
                    }
                }
            }
        }.launchIn(viewModelScope)
    }
}
package kr.co.younhwan.eatjnu.presentation.place_detail

import android.util.Log
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
import kr.co.younhwan.eatjnu.domain.model.PlaceDetailInfo
import kr.co.younhwan.eatjnu.domain.use_case.add_like_place.AddLikePlaceUseCase
import kr.co.younhwan.eatjnu.domain.use_case.create_user_id.CreateUserIdUseCase
import kr.co.younhwan.eatjnu.domain.use_case.get_like_place_list.GetLikePlaceListUseCase
import kr.co.younhwan.eatjnu.domain.use_case.get_place_detail.GetPlaceDetailUseCase
import kr.co.younhwan.eatjnu.domain.use_case.get_user_id.GetUserIdUseCase
import kr.co.younhwan.eatjnu.domain.use_case.remove_like_place.RemoveLikePlaceUseCase
import java.util.*
import javax.inject.Inject

@HiltViewModel
class PlaceDetailViewModel @Inject constructor(
    private val getPlaceDetailUseCase: GetPlaceDetailUseCase,
    private val addLikePlaceUseCase: AddLikePlaceUseCase,
    private val getLikePlaceListUseCase: GetLikePlaceListUseCase,
    private val removeLikePlaceUseCase: RemoveLikePlaceUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    /* State */
    var error = mutableStateOf("")
    var isLoading = mutableStateOf(true)

    val userId = mutableStateOf("")
    val isLikePlace = mutableStateOf(false)
    val placeDetail = mutableStateOf(
        PlaceDetailInfo(
            id = -1,
            name = "",
            likeCount = 0,
            reviewCount = 0,
            filter = "",
            tags = "",
            image = "",
            location = "",
            number = "",
            openingInfo = "",
            images = emptyList(),
            lat = 0.0,
            lon = 0.0
        )
    )

    /* Init */
    init {
        viewModelScope.launch {
            val placeId = savedStateHandle.get<String>(Constants.PARAM_PLACE_ID) ?: "-1"
            userId.value = savedStateHandle.get<String>(Constants.PARAM_USER_ID) ?: ""

            getPlaceDetail(placeId = placeId)
            checkLikePlace(userId = userId.value, placeId = placeId)
        }
    }

    /* Function */
    private fun getPlaceDetail(placeId: String)  {
        // 장소 리스트 초기화
        getPlaceDetailUseCase(placeId = placeId).onEach { result ->
            when (result) {
                is Resource.Loading -> {}
                is Resource.Error -> {
                    isLoading.value = false
                    error.value = result.message ?: ""
                }
                is Resource.Success -> {
                    isLoading.value = false
                    placeDetail.value = result.data ?: placeDetail.value
                }
            }
        }.launchIn(viewModelScope)
    }

    fun checkLikePlace(userId: String, placeId: String) {
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
                    placeDetail.value = placeDetail.value.copy(likeCount = placeDetail.value.likeCount?.plus(1))
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
                    placeDetail.value = placeDetail.value.copy(likeCount = placeDetail.value.likeCount?.minus(1))
                }
            }
        }.launchIn(viewModelScope)
    }
}
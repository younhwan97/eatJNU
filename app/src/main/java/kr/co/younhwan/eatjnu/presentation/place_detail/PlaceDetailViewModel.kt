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
import java.util.*
import javax.inject.Inject

@HiltViewModel
class PlaceDetailViewModel @Inject constructor(
    private val getPlaceDetailUseCase: GetPlaceDetailUseCase,
    private val createUserIdUseCase: CreateUserIdUseCase,
    private val getUserIdUseCase: GetUserIdUseCase,
    private val addLikePlaceUseCase: AddLikePlaceUseCase,
    private val getLikePlaceListUseCase: GetLikePlaceListUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    /* State */
    var error = mutableStateOf("")
    var isLoading = mutableStateOf(false)

    val userId = mutableStateOf("")
    val likePlaceList = mutableStateOf<List<Int>>(emptyList())
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
        savedStateHandle.get<String>(Constants.PARAM_PLACE_ID)?.let { placeId ->
            getPlaceDetail(placeId = placeId)
        }

        viewModelScope.launch {
            userId.value = getUserIdUseCase() ?: ""

            if (userId.value == ""){
                userId.value = UUID.randomUUID().toString()
                createUserIdUseCase(userId.value)
            }

            getLikePlaceList(userId.value)
        }
    }

    /* Function */
    private fun getPlaceDetail(placeId: String) {
        getPlaceDetailUseCase(placeId = placeId).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    isLoading.value = true
                }

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

    fun getLikePlaceList(userId: String) {
        getLikePlaceListUseCase(userId = userId).onEach { result ->
            when (result) {
                is Resource.Loading -> {

                }

                is Resource.Error -> {

                }

                is Resource.Success -> {
                    likePlaceList.value = result.data ?: emptyList()
                }
            }
        }.launchIn(viewModelScope)
    }

    fun addLikePlace(placeId: Int) {
        addLikePlaceUseCase(userId = userId.value, placeId = placeId.toString()).onEach {

        }.launchIn(viewModelScope)
    }
}
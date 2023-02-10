package kr.co.younhwan.eatjnu.presentation.place_detail

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kr.co.younhwan.eatjnu.common.Constants
import kr.co.younhwan.eatjnu.common.Resource
import kr.co.younhwan.eatjnu.domain.model.PlaceDetailInfo
import kr.co.younhwan.eatjnu.domain.use_case.get_place_detail.GetPlaceDetailUseCase
import javax.inject.Inject

@HiltViewModel
class PlaceDetailViewModel @Inject constructor(
    private val getPlaceDetailUseCase: GetPlaceDetailUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    /* State */
    var error = mutableStateOf("")
    var isLoading = mutableStateOf(false)

    val placeDetail = mutableStateOf<PlaceDetailInfo>(
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
        )
    )

    /* Init */
    init {
        savedStateHandle.get<String>(Constants.PARAM_PLACE_ID)?.let { placeId ->
            getPlaceDetail(placeId = placeId)
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
}
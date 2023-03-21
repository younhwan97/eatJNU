package kr.co.younhwan.eatjnu.presentation.place_like_list

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kr.co.younhwan.eatjnu.common.Constants
import kr.co.younhwan.eatjnu.common.Resource
import kr.co.younhwan.eatjnu.domain.model.PlaceSummary
import kr.co.younhwan.eatjnu.domain.use_case.get_like_place_list.GetLikePlaceListUseCase
import javax.inject.Inject

@HiltViewModel
class PlaceLikeListViewModel @Inject constructor(
    private val getLikePlaceListUseCase: GetLikePlaceListUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    var error = mutableStateOf("")
    var isLoading = mutableStateOf(true)

    val userId = mutableStateOf("")
    val likePlaces = mutableStateOf<List<PlaceSummary>>(listOf())

    init {
        // 1. 유저 아이디 값 초기화
        userId.value = savedStateHandle.get<String>(Constants.PARAM_USER_ID) ?: ""
        // 2. 장소 목록 초기화
        getLikePlaceList(userId.value)
    }

    // '좋아요'를 누른 장소를 가져오는 함수
    private fun getLikePlaceList(userId: String) {
        getLikePlaceListUseCase(userId = userId).onEach { result ->
            when (result) {
                is Resource.Loading -> isLoading.value = true
                is Resource.Error -> {
                    isLoading.value = false
                    error.value = result.message ?: "error"
                }
                is Resource.Success -> {
                    isLoading.value = false
                    likePlaces.value = result.data ?: emptyList()
                }
            }
        }.launchIn(viewModelScope)
    }
}
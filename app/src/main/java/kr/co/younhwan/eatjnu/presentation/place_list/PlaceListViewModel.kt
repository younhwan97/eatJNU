package kr.co.younhwan.eatjnu.presentation.place_list

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kr.co.younhwan.eatjnu.common.Constants
import kr.co.younhwan.eatjnu.common.Resource
import kr.co.younhwan.eatjnu.domain.model.FilterInfo
import kr.co.younhwan.eatjnu.domain.model.PlaceInfo
import kr.co.younhwan.eatjnu.domain.use_case.get_filter.GetFilterUseCase
import kr.co.younhwan.eatjnu.domain.use_case.get_place_list.GetPlaceListUseCase
import javax.inject.Inject

@HiltViewModel
class PlaceListViewModel @Inject constructor(
    private val getFilterUseCase: GetFilterUseCase,
    private val getPlaceListUseCase: GetPlaceListUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    /* State */
    var error = mutableStateOf("")
    var isLoading = mutableStateOf(false)

    val filterList = mutableStateOf<List<FilterInfo>>(listOf())

    var area = mutableStateOf("")
    var selectedFilter = mutableStateOf(1)
    var placeList = mutableStateOf<List<PlaceInfo>>(listOf())
    val userId = mutableStateOf("")

    /* Init */
    init {
        // 필터 리스트 초기화
        filterList.value = getFilterUseCase()

        savedStateHandle.get<String>(Constants.PARAM_AREA_TYPE)?.let { areaType ->
            // 장소 리스트 초기화
            getPlaceList(areaType)

            // area 변수 초기화
            when (areaType) {
                "0" -> area.value = "후문"
                "1" -> area.value = "상대"
                "2" -> area.value = "정문"
            }
        }

        savedStateHandle.get<String>(Constants.PARAM_USER_ID)?.let { userId ->
            this.userId.value = userId
        }
    }

    /* Function */
    private fun getPlaceList(type: String) {
        getPlaceListUseCase(type).onEach { result ->
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
                    placeList.value = result.data ?: emptyList()
                }
            }
        }.launchIn(viewModelScope)
    }

    fun changeFilter(num: Int) {
        selectedFilter.value = num
    }
}
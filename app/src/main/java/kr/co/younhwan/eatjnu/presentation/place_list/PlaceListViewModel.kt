package kr.co.younhwan.eatjnu.presentation.place_list

import androidx.compose.runtime.State
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
import kr.co.younhwan.eatjnu.domain.model.Place
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
    var placeList = mutableStateOf<List<Place>>(listOf())
    var error = mutableStateOf("")
    var isLoading = mutableStateOf(false)
    var selectedFilter = mutableStateOf(1)
    val filterList = mutableStateOf<List<FilterInfo>>(listOf())

    /* Init */
    init {
        // 필터 정보 초기화
        filterList.value = getFilterUseCase()

        // Area Type에 따라 장소 리스트 로드
        savedStateHandle.get<String>(Constants.PARAM_AREA_TYPE)?.let { areaType ->
            getPlaceList(areaType)
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
                    placeList.value = emptyList()
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
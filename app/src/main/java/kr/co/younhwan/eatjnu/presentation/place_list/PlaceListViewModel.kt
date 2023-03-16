package kr.co.younhwan.eatjnu.presentation.place_list

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kr.co.younhwan.eatjnu.common.Constants
import kr.co.younhwan.eatjnu.common.Resource
import kr.co.younhwan.eatjnu.domain.model.Filter
import kr.co.younhwan.eatjnu.domain.model.PlaceSummary
import kr.co.younhwan.eatjnu.domain.use_case.get_filter_list.GetFilterListUseCase
import kr.co.younhwan.eatjnu.domain.use_case.get_place_list.GetPlaceListUseCase
import javax.inject.Inject

@HiltViewModel
class PlaceListViewModel @Inject constructor(
    private val getFilterListUseCase: GetFilterListUseCase,
    private val getPlaceListUseCase: GetPlaceListUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    var error = mutableStateOf("")
    var isLoading = mutableStateOf(false)

    val userId = mutableStateOf("")
    val areaTypeName = mutableStateOf("")
    var selectedFilter = mutableStateOf("맛집")
    val filters = mutableStateOf<List<Filter>>(listOf())
    var places = mutableStateOf<List<PlaceSummary>>(listOf())

    init {
        // 1. 유저 아이디 값 초기화
        userId.value = savedStateHandle.get<String>(Constants.PARAM_USER_ID) ?: ""
        // 2. 지역 정보 초기화
        val areaType = savedStateHandle.get<String>(Constants.PARAM_AREA_TYPE) ?: "1"
        when (areaType) {
            "0" -> areaTypeName.value = "후문"
            "1" -> areaTypeName.value = "상대"
            "2" -> areaTypeName.value = "정문"
        }
        // 3. 필터 목록 초기화
        getFilterList()
        // 4. 장소 목록 초기화
        getPlaceList(areaType)
    }

    private fun getFilterList() {
        filters.value = getFilterListUseCase()
    }

    fun changeFilter(newFilter: String) {
        selectedFilter.value = newFilter
    }

    private fun getPlaceList(areaType: String) {
        getPlaceListUseCase(areaType).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    isLoading.value = true
                }

                is Resource.Error -> {
                    isLoading.value = false
                    error.value = result.message ?: "error"
                }

                is Resource.Success -> {
                    isLoading.value = false
                    places.value = result.data ?: emptyList()
                }
            }
        }.launchIn(viewModelScope)
    }
}
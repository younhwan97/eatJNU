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

    var error = mutableStateOf("")
    var isLoading = mutableStateOf(false)

    val filterList = mutableStateOf<List<FilterInfo>>(listOf())

    var areaType = mutableStateOf("")
    var selectedFilter = mutableStateOf("맛집")
    var placeList = mutableStateOf<List<PlaceInfo>>(listOf())
    val userId = mutableStateOf("")

    init {
        filterList.value = getFilterList()

        savedStateHandle.get<String>(Constants.PARAM_AREA_TYPE)?.let { areaType ->
            getPlaceList(areaType)

            when (areaType) {
                "0" -> this.areaType.value = "후문"
                "1" -> this.areaType.value = "상대"
                "2" -> this.areaType.value = "정문"
            }
        }

        savedStateHandle.get<String>(Constants.PARAM_USER_ID)?.let { userId ->
            this.userId.value = userId
        }
    }

    private fun getFilterList() = getFilterUseCase()

    fun changeFilter(newFilter: String) {
        selectedFilter.value = newFilter
    }

    private fun getPlaceList(type: String) {
        getPlaceListUseCase(type).onEach { result ->
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
                    placeList.value = result.data ?: emptyList()
                }
            }
        }.launchIn(viewModelScope)
    }
}
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
import kr.co.younhwan.eatjnu.domain.use_case.get_filter.GetFilterUseCase
import kr.co.younhwan.eatjnu.domain.use_case.get_place_list.GetPlaceListUseCase
import javax.inject.Inject

@HiltViewModel
class PlaceListViewModel @Inject constructor(
    private val getFilterUseCase: GetFilterUseCase,
    private val getPlaceListUseCase: GetPlaceListUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _state = mutableStateOf(PlaceListState())
    val state: State<PlaceListState> = _state

    init {
        savedStateHandle.get<String>(Constants.PARAM_AREA_TYPE)?.let { areaType ->
            getPlaceList(areaType)
        }
    }

    private fun getPlaceList(type: String) {
        getPlaceListUseCase(type).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _state.value = PlaceListState(isLoading = true)
                }

                is Resource.Error -> {
                    _state.value = PlaceListState(error = result.message ?: "")
                }

                is Resource.Success -> {
                    _state.value = PlaceListState(
                        data = result.data ?: emptyList(),
                        filter = getFilterUseCase() ?: emptyList()
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
}
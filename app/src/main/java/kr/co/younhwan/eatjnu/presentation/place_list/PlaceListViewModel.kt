package kr.co.younhwan.eatjnu.presentation.place_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kr.co.younhwan.eatjnu.common.Resource
import kr.co.younhwan.eatjnu.domain.use_case.get_place_list.GetPlaceListUseCase
import javax.inject.Inject

@HiltViewModel
class PlaceListViewModel @Inject constructor(
    private val getPlaceListUseCase: GetPlaceListUseCase
) : ViewModel() {
    private val _state = mutableStateOf(PlaceListState())
    val state: State<PlaceListState> = _state

    init {
        getPlaceList()
    }

    private fun getPlaceList() {
        getPlaceListUseCase().onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _state.value = PlaceListState(isLoading = true)
                }

                is Resource.Error -> {
                    _state.value = PlaceListState(error = result.message ?: "")
                }

                is Resource.Success -> {
                    _state.value = PlaceListState(data = result.data ?: emptyList())
                }
            }
        }.launchIn(viewModelScope)
    }
}
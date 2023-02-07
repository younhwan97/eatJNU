package kr.co.younhwan.eatjnu.presentation.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kr.co.younhwan.eatjnu.domain.use_case.get_area.GetAreaUseCase
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAreaUseCase: GetAreaUseCase
) : ViewModel() {
    private val _state = mutableStateOf(HomeState())
    val state: State<HomeState> = _state

    init {
        getArea()
    }

    private fun getArea() {
        _state.value = HomeState(data = getAreaUseCase())
    }
}
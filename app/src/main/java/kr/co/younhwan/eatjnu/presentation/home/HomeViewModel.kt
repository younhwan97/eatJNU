package kr.co.younhwan.eatjnu.presentation.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kr.co.younhwan.eatjnu.domain.model.AreaInfo
import kr.co.younhwan.eatjnu.domain.use_case.get_area.GetAreaUseCase
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAreaUseCase: GetAreaUseCase
) : ViewModel() {

    /* State */
    var areaList = mutableStateOf<List<AreaInfo>>(listOf())

    /* Init */
    init {
        getArea()
    }

    /* Function */
    private fun getArea() {
        areaList.value = getAreaUseCase()
    }
}
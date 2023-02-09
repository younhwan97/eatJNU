package kr.co.younhwan.eatjnu.presentation.home

import kr.co.younhwan.eatjnu.domain.model.AreaInfo

data class HomeState(
    val isLoading: Boolean = false,
    val error: String = "",
    val data: List<AreaInfo> = emptyList()
)
package kr.co.younhwan.eatjnu.presentation.home

import kr.co.younhwan.eatjnu.domain.model.Area

data class HomeState(
    val isLoading: Boolean = false,
    val error: String = "",
    val data: List<Area> = emptyList()
)
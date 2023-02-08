package kr.co.younhwan.eatjnu.presentation.place_list

import kr.co.younhwan.eatjnu.domain.model.Area
import kr.co.younhwan.eatjnu.domain.model.FilterInfo
import kr.co.younhwan.eatjnu.domain.model.Place

data class PlaceListState(
    val isLoading: Boolean = false,
    val error: String = "",
    val data: List<Place> = emptyList(),
    val filter: List<FilterInfo> = emptyList(),
    val selectedFilter: Int = 1
)
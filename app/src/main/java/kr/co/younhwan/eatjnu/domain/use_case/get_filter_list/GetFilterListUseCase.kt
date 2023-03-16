package kr.co.younhwan.eatjnu.domain.use_case.get_filter_list

import kr.co.younhwan.eatjnu.R
import kr.co.younhwan.eatjnu.domain.model.Filter
import javax.inject.Inject

class GetFilterListUseCase @Inject constructor() {
    operator fun invoke() = listOf(
        Filter(
            resource = R.drawable.ic_outline_place_24,
            text = "전체"
        ),
        Filter(
            resource = R.drawable.ic_outline_local_dining_24,
            text = "맛집"
        ),
        Filter(
            resource = R.drawable.ic_outline_wine_bar_24,
            text = "술집"
        ),
        Filter(
            resource = R.drawable.ic_outline_local_cafe_24,
            text = "카페"
        )
    )
}
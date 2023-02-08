package kr.co.younhwan.eatjnu.domain.use_case.get_filter

import kr.co.younhwan.eatjnu.R
import kr.co.younhwan.eatjnu.domain.model.FilterInfo
import javax.inject.Inject

class GetFilterUseCase @Inject constructor() {
    val data = listOf(
        FilterInfo(
            resource = R.drawable.ic_outline_place_24,
            text = "전체"
        ),
        FilterInfo(
            resource = R.drawable.ic_outline_local_dining_24,
            text = "맛집"
        ),
        FilterInfo(
            resource = R.drawable.ic_outline_wine_bar_24,
            text = "술집"
        ),
        FilterInfo(
            resource = R.drawable.ic_outline_local_cafe_24,
            text = "카페"
        )
    )

    operator fun invoke(): List<FilterInfo> {
        return data
    }
}
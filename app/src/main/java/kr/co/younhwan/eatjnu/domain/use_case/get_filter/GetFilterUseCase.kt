package kr.co.younhwan.eatjnu.domain.use_case.get_filter

import kr.co.younhwan.eatjnu.R
import kr.co.younhwan.eatjnu.domain.model.FilterInfo
import javax.inject.Inject

class GetFilterUseCase @Inject constructor() {
    val data = listOf(
        FilterInfo(
            resource = R.drawable.ic_baseline_local_dining_24,
            text = "맛집"
        ),
        FilterInfo(
            resource = R.drawable.ic_baseline_wine_bar_24,
            text = "술집"
        )
    )

    operator fun invoke(): List<FilterInfo> {
        return data
    }
}
package kr.co.younhwan.eatjnu.domain.use_case.get_area

import kr.co.younhwan.eatjnu.domain.model.Area
import javax.inject.Inject

class GetAreaUseCase @Inject constructor() {
    operator fun invoke() = listOf(
        Area(type = 0, title = "후문"),
        Area(type = 1, title = "상대"),
        Area(type = 2, title = "정문")
    )
}
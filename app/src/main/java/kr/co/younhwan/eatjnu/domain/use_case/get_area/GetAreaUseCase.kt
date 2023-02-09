package kr.co.younhwan.eatjnu.domain.use_case.get_area

import kr.co.younhwan.eatjnu.domain.model.AreaInfo
import javax.inject.Inject

class GetAreaUseCase @Inject constructor() {
    val data = listOf(
        AreaInfo(type = 0, title = "후문"),
        AreaInfo(type = 1, title = "상대"),
        AreaInfo(type = 2, title = "정문")
    )

    operator fun invoke(): List<AreaInfo> {
        return data
    }
}
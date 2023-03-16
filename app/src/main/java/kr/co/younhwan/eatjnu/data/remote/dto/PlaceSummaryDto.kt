package kr.co.younhwan.eatjnu.data.remote.dto

import kr.co.younhwan.eatjnu.domain.model.PlaceSummary

// 장소의 모든 정보를 요청할 때가 아닌
// 장소 리스트 화면에서 요약된 정보를 보여줄 때 사용

// const query = "SELECT store_id, name, review_count, like_count, tags, filter, url FROM store WHERE area = ?;"

data class PlaceSummaryListDto(
    val items: List<PlaceSummaryDto>?
)

data class PlaceSummaryDto(
    val id: Int?,
    val name: String?,
    val image: String?,
    val reviewCount: Int?,
    val likeCount: Int?,
    val tags: String?,
    val filter: String?,
)

fun PlaceSummaryDto.toPlaceSummary(): PlaceSummary {
    return PlaceSummary(
        id = id ?: -1,
        name = name ?: "",
        image = image ?: "",
        reviewCount = reviewCount ?: 0,
        likeCount = likeCount ?: 0,
        tags = tags ?: "",
        filter = filter ?: "맛집"
    )
}
package kr.co.younhwan.eatjnu.domain.model

data class PlaceSummary(
    val id: Int,
    val name: String,
    val image: String,
    val reviewCount: Int,
    val likeCount: Int,
    val tags: String,
    val filter: String,
)

package kr.co.younhwan.eatjnu.domain.model

data class Place(
    val id: Int,
    val name: String,
    val image: String?,
    val reviewCount: Int,
    val likeCount: Int,
    val tags: String?,
    val filter: String,
)

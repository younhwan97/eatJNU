package kr.co.younhwan.eatjnu.data.remote.dto

data class PlaceInfo(
    val id: Int,
    val name: String,
    val image: String,
    val reviewCount: Int,
    val likeCount: Int,
    val tags: String
)

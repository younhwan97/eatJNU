package kr.co.younhwan.eatjnu.domain.model

data class PlaceDetailInfo(
    val id: Int,
    val name: String,
    val likeCount: Int?,
    val reviewCount: Int?,
    val filter: String,
    val tags: String?,
    val image: String?,
    val images: List<FoodImageInfo>,
    val location: String?,
    val number: String?,
    val openingInfo: String?,
)
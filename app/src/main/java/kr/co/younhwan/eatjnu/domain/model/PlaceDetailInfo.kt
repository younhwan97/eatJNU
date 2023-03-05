package kr.co.younhwan.eatjnu.domain.model

data class PlaceDetailInfo(
    val id: Int,
    val name: String,
    val likeCount: Int?,
    val reviewCount: Int?,
    val filter: String,
    val tags: String? = null,
    val image: String?,
    val images: List<FoodImageInfo>,
    val location: String,
    val number: String? = null,
    val openingInfo: String? = null,
    val lat: Double,
    val lon: Double,
    val reviews: List<ReviewInfo>
)
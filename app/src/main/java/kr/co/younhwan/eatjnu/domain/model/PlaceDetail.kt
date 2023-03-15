package kr.co.younhwan.eatjnu.domain.model

data class PlaceDetail(
    val id: Int,
    val name: String,
    val likeCount: Int,
    val reviewCount: Int,
    val filter: String,
    val tags: String,
    val image: String,
    val location: String,
    val lat: Double,
    val lon: Double,
    val number: String,
    val openingInfo: String,
    val placeImages: List<PlaceImage>,
    val placeReviews: List<PlaceReview>
)
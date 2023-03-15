package kr.co.younhwan.eatjnu.data.remote.dto

import kr.co.younhwan.eatjnu.domain.model.PlaceDetail

// mysql> desc store;
// +--------------+--------------+------+-----+---------+----------------+
// | Field        | Type         | Null | Key | Default | Extra          |
// +--------------+--------------+------+-----+---------+----------------+
// | store_id     | int unsigned | NO   | PRI | NULL    | auto_increment |
// | name         | char(15)     | NO   |     | NULL    |                |
// | area         | int unsigned | NO   |     | NULL    |                |
// | filter       | char(10)     | NO   |     | NULL    |                |
// | tags         | text         | YES  |     | NULL    |                |
// | location     | text         | YES  |     | NULL    |                |
// | number       | char(20)     | YES  |     | NULL    |                |
// | opening_info | text         | YES  |     | NULL    |                |
// | url          | text         | YES  |     | NULL    |                |
// | lat          | double       | YES  |     | NULL    |                |
// | lon          | double       | YES  |     | NULL    |                |
// | like_count   | int          | YES  |     | 0       |                |
// | review_count | int          | YES  |     | 0       |                |
// +--------------+--------------+------+-----+---------+----------------+

// DTO의 경우 Null 값을 허용하되,
// Model의 Data class를 Null 값을 허용하지 않도록 처리

data class PlaceDetailDto(
    val id: Int?,
    val name: String?,
    val likeCount: Int?,
    val reviewCount: Int?,
    var filter: String?,
    val tags: String?,
    val image: String?,
    val location: String?,
    val number: String?,
    val openingInfo: String?,
    val images: List<PlaceImageDto>?,
    val lat: Double?,
    val lon: Double?,
    val reviews: List<PlaceReviewDto>?
)

fun PlaceDetailDto.toPlaceDetail(): PlaceDetail {
    return PlaceDetail(
        id = id ?: -1,
        name = name ?: "",
        likeCount = likeCount ?: 0,
        reviewCount = reviewCount ?: 0,
        filter = filter ?: "맛집",
        tags = tags ?: "",
        image = image ?: "",
        location = location ?: "",
        number = number ?: "",
        openingInfo = openingInfo ?: "",
        lat = lat ?: 0.0,
        lon = lon ?: 0.0,
        placeImages = images?.map { it.toPlaceImage() } ?: emptyList(),
        placeReviews = reviews?.map { it.toPlaceReview() } ?: emptyList()
    )
}
package kr.co.younhwan.eatjnu.domain.model

data class ReviewInfo(
    val name: String? = null,
    val comment: String? = null,
    val writingTime: String? = null,
    val userId: String? = null,
    val likeCount: Int? = 0
)

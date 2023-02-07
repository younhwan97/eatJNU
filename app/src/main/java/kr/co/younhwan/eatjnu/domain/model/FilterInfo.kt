package kr.co.younhwan.eatjnu.domain.model

data class FilterInfo(
    val resource: Int,
    val text: String,
    val selected: Boolean = false
)

package kr.co.younhwan.eatjnu.data.preferences

interface Preferences {

    suspend fun getUserId(): String?

    suspend fun createUserId(id: String)
}
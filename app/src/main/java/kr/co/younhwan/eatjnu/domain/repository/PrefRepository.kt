package kr.co.younhwan.eatjnu.domain.repository

interface PrefRepository {

    suspend fun getUserId(): String?

    suspend fun createUserId(id: String)
}
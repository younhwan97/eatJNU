package kr.co.younhwan.eatjnu.data.repository

import kr.co.younhwan.eatjnu.data.preferences.Preferences
import kr.co.younhwan.eatjnu.domain.repository.PrefRepository
import javax.inject.Inject

class PrefRepositoryImpl @Inject constructor(
    private val preferences: Preferences
) : PrefRepository {

    override suspend fun getUserId(): String? {
        return preferences.getUserId()
    }

    override suspend fun createUserId(id: String) {
        preferences.createUserId(id)
    }
}
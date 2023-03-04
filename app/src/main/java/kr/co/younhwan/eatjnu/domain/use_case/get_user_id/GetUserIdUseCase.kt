package kr.co.younhwan.eatjnu.domain.use_case.get_user_id

import android.util.Log
import kr.co.younhwan.eatjnu.domain.repository.PrefRepository
import javax.inject.Inject

class GetUserIdUseCase @Inject constructor(
    private val repository: PrefRepository
) {

    suspend operator fun invoke(): String? {
        return repository.getUserId()
    }
}
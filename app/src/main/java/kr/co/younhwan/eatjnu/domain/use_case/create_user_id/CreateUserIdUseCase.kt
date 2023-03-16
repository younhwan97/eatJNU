package kr.co.younhwan.eatjnu.domain.use_case.create_user_id

import kr.co.younhwan.eatjnu.domain.repository.PrefRepository
import javax.inject.Inject

class CreateUserIdUseCase @Inject constructor(
    private val repository: PrefRepository
) {
    suspend operator fun invoke(id: String) = repository.createUserId(id)
}
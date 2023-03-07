package kr.co.younhwan.eatjnu.presentation.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kr.co.younhwan.eatjnu.domain.model.Area
import kr.co.younhwan.eatjnu.domain.use_case.create_user_id.CreateUserIdUseCase
import kr.co.younhwan.eatjnu.domain.use_case.get_area.GetAreaUseCase
import kr.co.younhwan.eatjnu.domain.use_case.get_user_id.GetUserIdUseCase
import java.util.*
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAreaUseCase: GetAreaUseCase,
    private val getUserIdUseCase: GetUserIdUseCase,
    private val createUserIdUseCase: CreateUserIdUseCase
) : ViewModel() {

    var areas = mutableStateOf<List<Area>>(listOf())
    val userId = mutableStateOf("")

    init {
        // 1. 지역 정보 초기화
        getArea()
        viewModelScope.launch {
            // 2. 유저 아이디 초기화
            getUserId()
            // 3. (아이디가 없는 경우) 유저 아이디 생성 및 초기화
            if (userId.value == "") {
                createUserId()
                getUserId()
            }
        }
    }

    private fun getArea() {
        areas.value = getAreaUseCase()
    }

    private suspend fun getUserId() {
        userId.value = getUserIdUseCase()
    }

    private suspend fun createUserId() {
        createUserIdUseCase(UUID.randomUUID().toString())
    }
}
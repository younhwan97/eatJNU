package kr.co.younhwan.eatjnu.presentation.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kr.co.younhwan.eatjnu.domain.model.Area
import kr.co.younhwan.eatjnu.domain.use_case.create_user_id.CreateUserIdUseCase
import kr.co.younhwan.eatjnu.domain.use_case.get_area_list.GetAreaListUseCase
import kr.co.younhwan.eatjnu.domain.use_case.get_user_id.GetUserIdUseCase
import java.util.*
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAreaListUseCase: GetAreaListUseCase,
    private val getUserIdUseCase: GetUserIdUseCase,
    private val createUserIdUseCase: CreateUserIdUseCase
) : ViewModel() {

    val areas = mutableStateOf<List<Area>>(listOf())
    val userId = mutableStateOf("")

    init {
        viewModelScope.launch {
            // 1. 지역 목록 초기화
            getAreaList()
            // 2. 유저 아이디 초기화
            getUserId()
            // 3. (아이디가 없는 경우) 유저 아이디 생성 및 초기화
            if (userId.value.isBlank()) {
                createUserId()
                getUserId()
            }
        }
    }

    // 지역 목록을 초기화하는 함수
    private fun getAreaList() {
        areas.value = getAreaListUseCase()
    }

    // 유저 ID를 가져오는 함수
    private suspend fun getUserId() {
        userId.value = getUserIdUseCase()
    }

    // 유저 ID를 생성하는 함수
    private suspend fun createUserId() {
        createUserIdUseCase(UUID.randomUUID().toString())
    }
}
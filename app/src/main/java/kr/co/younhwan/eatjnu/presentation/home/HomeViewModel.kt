package kr.co.younhwan.eatjnu.presentation.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kr.co.younhwan.eatjnu.domain.model.AreaInfo
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

    var areaList = mutableStateOf<List<AreaInfo>>(listOf())
    val userId = mutableStateOf("")

    init {
        getArea()

        viewModelScope.launch {
            getUserId()
            if (userId.value == "") {
                createUserId()
                getUserId()
            }
        }
    }

    // 후문, 상대, 정문 등의 '지역' 정보를 초기화
    private fun getArea() {
        areaList.value = getAreaUseCase()
    }

    // 유저 아이디 초기화
    private suspend fun getUserId() {
        userId.value = getUserIdUseCase()
    }

    // 유저 아이디 생성
    private suspend fun createUserId() {
        createUserIdUseCase(UUID.randomUUID().toString())
    }
}
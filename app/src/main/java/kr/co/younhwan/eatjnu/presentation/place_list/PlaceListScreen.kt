package kr.co.younhwan.eatjnu.presentation.place_list

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kr.co.younhwan.eatjnu.presentation.place_list.components.FilterScreen
import kr.co.younhwan.eatjnu.presentation.place_list.components.PlaceScreen
import kr.co.younhwan.eatjnu.presentation.supprot.ErrorScreen
import kr.co.younhwan.eatjnu.presentation.supprot.LoadingScreen
import kr.co.younhwan.eatjnu.presentation.supprot.MyTopAppBar

@Composable
fun PlaceListScreen(
    navController: NavController,
    viewModel: PlaceListViewModel = hiltViewModel()
) {
    val isLoading by remember { viewModel.isLoading }
    val error by remember { viewModel.error }

    if (isLoading) {
        LoadingScreen()
    } else if (error.isNotEmpty()) {
        ErrorScreen()
    } else {
        val userId by remember { viewModel.userId } // 유저 ID
        val filters by remember { viewModel.filters } // 필터 목록
        val selectedFilter by remember { viewModel.selectedFilter } // (전체, 맛집, 술집, 카페) 선택된 필터
        val areaTypeName by remember { viewModel.areaTypeName } // (정문, 후문, 상대) 지역 타입
        val places by remember { viewModel.places } // 장소 목록

        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // 앱바
            MyTopAppBar(
                title = areaTypeName,
                navController = navController
            )

            // 필터 목록
            FilterScreen(
                filters = filters,
                selectedFilter = selectedFilter,
                viewModel = viewModel
            )

            // 장소 목록
            PlaceScreen(
                userId = userId,
                selectedFilter = selectedFilter,
                places = places,
                navController = navController
            )
        }
    }
}
package kr.co.younhwan.eatjnu.presentation.place_list

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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
    val placeList by remember { viewModel.placeList }

    if (isLoading) {
        LoadingScreen()
    } else if (error.isNotEmpty()) {
        ErrorScreen()
    } else {
        val areaType by remember { viewModel.areaType } // 정문, 후문, 상대 등의 지역정보
        val selectedFilter by remember { viewModel.selectedFilter } // 선택된 필터 이름(전체, 맛집, 술집, 카페)

        val filterList by remember { viewModel.filterList }
        val userId by remember { viewModel.userId }

        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // 앱바
            MyTopAppBar(
                title = areaType,
                navController = navController
            )

            // 필터 리스트
            FilterScreen(
                selectedFilter = selectedFilter,
                filters = filterList,
                viewModel = viewModel
            )

            Spacer(modifier = Modifier.height(4.dp))

            Divider(modifier = Modifier.height(1.dp))

            // 장소 리스트
            PlaceScreen(
                navController = navController,
                places = placeList,
                selectedFilter = selectedFilter,
                userId = userId,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}
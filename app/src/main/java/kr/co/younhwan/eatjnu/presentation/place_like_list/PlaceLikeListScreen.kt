package kr.co.younhwan.eatjnu.presentation.place_like_list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kr.co.younhwan.eatjnu.presentation.place_list.components.PlaceScreen
import kr.co.younhwan.eatjnu.presentation.supprot.ErrorScreen
import kr.co.younhwan.eatjnu.presentation.supprot.LoadingScreen
import kr.co.younhwan.eatjnu.presentation.supprot.MyTopAppBar

@Composable
fun PlaceLikeListScreen(
    navController: NavController,
    viewModel: PlaceLikeListViewModel = hiltViewModel()
) {
    val isLoading by remember { viewModel.isLoading }
    val error by remember { viewModel.error }

    if (isLoading) {
        LoadingScreen()
    } else if (error.isNotEmpty()) {
        ErrorScreen()
    } else {
        val userId by remember { viewModel.userId } // 유저 ID
        val likePlaces by remember { viewModel.likePlaces } // 장소 목록

        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // 앱바
            MyTopAppBar(
                title = "좋아요",
                navController = navController
            )

            // 장소 목록
            PlaceScreen(
                userId = userId,
                selectedFilter = "전체",
                places = likePlaces,
                navController = navController
            )
        }
    }
}
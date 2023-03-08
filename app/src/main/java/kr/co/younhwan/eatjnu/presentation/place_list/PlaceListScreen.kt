package kr.co.younhwan.eatjnu.presentation.place_list

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
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
import soup.compose.material.motion.MaterialMotion
import soup.compose.material.motion.animation.materialFadeThrough
import soup.compose.material.motion.animation.materialSharedAxisX
import soup.compose.material.motion.animation.materialSharedAxisZ
import soup.compose.material.motion.animation.rememberSlideDistance

@OptIn(ExperimentalAnimationApi::class)
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
        val userId by remember { viewModel.userId } // 유저 식별값
        val areaType by remember { viewModel.areaType } // 정문, 후문, 상대 등의 지역타입
        val selectedFilter by remember { viewModel.selectedFilter } // 선택된 필터 이름(전체, 맛집, 술집, 카페)
        val filters by remember { viewModel.filters } // 필터 리스트
        val places by remember { viewModel.places } // 장소 리스트

        val isVisiblePlaceScreen by remember { viewModel.isVisiblePlaceScreen }

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
                filters = filters,
                viewModel = viewModel
            )

            Spacer(modifier = Modifier.height(4.dp))

            // 장소 리스트
            val slideDistance = rememberSlideDistance(slideDistance = 40.dp)
            MaterialMotion(targetState = isVisiblePlaceScreen, transitionSpec = { materialSharedAxisX(forward = false, slideDistance = slideDistance) }, pop = true) {
                PlaceScreen(
                    userId = userId,
                    selectedFilter = selectedFilter,
                    places = places,
                    navController = navController,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}
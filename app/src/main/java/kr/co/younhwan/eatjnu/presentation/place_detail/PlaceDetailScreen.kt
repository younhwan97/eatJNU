package kr.co.younhwan.eatjnu.presentation.place_detail

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kr.co.younhwan.eatjnu.presentation.place_detail.components.ImageSlider
import kr.co.younhwan.eatjnu.presentation.place_detail.components.PlaceInfo
import kr.co.younhwan.eatjnu.presentation.supprot.ErrorScreen
import kr.co.younhwan.eatjnu.presentation.supprot.LoadingScreen
import kr.co.younhwan.eatjnu.presentation.supprot.MyTopAppBar

@Composable
fun PlaceDetailScreen(
    navController: NavController,
    viewModel: PlaceDetailViewModel = hiltViewModel()
) {
    /* State */
    val error by remember { viewModel.error }
    val isLoading by remember { viewModel.isLoading }

    val placeDetail by remember { viewModel.placeDetail }
    val isLikePlace by remember { viewModel.isLikePlace }

    /* UI */
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        if (isLoading) {
            // Loading
            LoadingScreen()
        } else if (placeDetail.id != -1) {
            // Success
            MyTopAppBar(
                title = placeDetail.name,
                navController = navController,
                isVisibleLikeBtn = true,
                isLikePlace = isLikePlace,
                onClickLikeBtn = {
                    if (isLikePlace) {
                        viewModel.removeLikePlace(placeId = placeDetail.id)
                    } else {
                        viewModel.addLikePlace(placeId = placeDetail.id)
                    }
                }
            )

            Divider(
                thickness = (0.5).dp,
                color = Color.LightGray
            )

            ImageSlider(
                mainImage = placeDetail.image ?: "",
                images = placeDetail.images
            )

            Divider(
                thickness = (0.5).dp,
                color = Color.LightGray
            )

            PlaceInfo(
                info = placeDetail
            )

        } else {
            // Error
            ErrorScreen()
            Log.e("error", "Error on PlaceDetail: $error")
        }
    }
} 
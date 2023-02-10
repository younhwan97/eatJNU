package kr.co.younhwan.eatjnu.presentation.place_detail

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kr.co.younhwan.eatjnu.presentation.place_detail.components.Header
import kr.co.younhwan.eatjnu.presentation.place_detail.components.ImageModal
import kr.co.younhwan.eatjnu.presentation.place_detail.components.ImageScreen
import kr.co.younhwan.eatjnu.presentation.supprot.ErrorScreen
import kr.co.younhwan.eatjnu.presentation.supprot.LoadingScreen

@Composable
fun PlaceDetailScreen(
    navController: NavController,
    viewModel: PlaceDetailViewModel = hiltViewModel()
) {
    /* State */
    val error by remember { viewModel.error }
    val isLoading by remember { viewModel.isLoading }

    val placeDetail by remember { viewModel.placeDetail }
    val modalImageUrl by remember { viewModel.modalImageUrl }

    /* UI */
    Column {
        if (isLoading) {
            // Loading
            LoadingScreen()
        } else if (placeDetail.id != -1) {
            // Success
            Header(
                placeDetail = placeDetail,
                navController = navController
            )

            Divider(
                color = Color(0XFFF4F4F4),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp)
            )

            if (placeDetail.images.isNotEmpty()) {
                ImageScreen(images = placeDetail.images, viewModel = viewModel)

                Divider(
                    color = Color(0XFFF4F4F4),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(8.dp)
                )

                if (modalImageUrl != "") {
                    ImageModal(url = modalImageUrl, viewModel = viewModel)
                }
            }

        } else {
            // Error
            ErrorScreen()
            Log.e("error", "Error on PlaceDetail: $error")
        }
    }
}
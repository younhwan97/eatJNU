package kr.co.younhwan.eatjnu.presentation.place_detail

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
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

    /* UI */
    if (isLoading) {
        // Loading
        LoadingScreen()
    } else if (placeDetail.id != -1) {

    } else {
        // Error
        ErrorScreen()
        Log.e("error", "Error on PlaceDetail: $error")
    }
} 
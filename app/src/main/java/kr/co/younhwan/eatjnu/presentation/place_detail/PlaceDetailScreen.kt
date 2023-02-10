package kr.co.younhwan.eatjnu.presentation.place_detail

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kr.co.younhwan.eatjnu.presentation.Screen
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

    /* UI */
    Column {
        MyTopAppBar(
            title = placeDetail.name,
            navController = navController,
            isVisibleHomeBtn = true
        )

        if (isLoading) {
            // Loading
            LoadingScreen()
        } else if (placeDetail.id != -1) {
            // Success

        } else {
            // Error
            ErrorScreen()
            Log.e("error", "Error on PlaceDetail: $error")
        }
    }
}
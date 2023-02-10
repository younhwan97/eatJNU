package kr.co.younhwan.eatjnu.presentation.place_list

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.getValue
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
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
    /* State */
    val isLoading by remember { viewModel.isLoading }
    val error by remember { viewModel.error }

    val area by remember { viewModel.area }
    val selectedFilter by remember { viewModel.selectedFilter }
    val placeList by remember { viewModel.placeList }
    val filterList by remember { viewModel.filterList }

    /* UI */
    Column {
        MyTopAppBar(
            title = area,
            navController = navController
        )

        FilterScreen(
            filters = filterList,
            selectedFilterNum = selectedFilter,
            viewModel = viewModel
        )

        Spacer(modifier = Modifier.height(8.dp))

        Divider(modifier = Modifier.height(1.dp))

        if (isLoading) {
            // Loading
            LoadingScreen()
        } else if (placeList.isNotEmpty()) {
            // Success
            PlaceScreen(
                navController = navController,
                places = placeList,
                selectedFilterNum = selectedFilter,
                modifier = Modifier.fillMaxSize()
            )
        } else {
            // Error
            ErrorScreen()
            Log.e("error", "Error on PlaceList: $error")
        }
    }
}
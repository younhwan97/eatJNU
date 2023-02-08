package kr.co.younhwan.eatjnu.presentation.place_list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.*
import androidx.compose.runtime.getValue
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kr.co.younhwan.eatjnu.presentation.place_list.components.FilterScreen
import kr.co.younhwan.eatjnu.presentation.place_list.components.PlaceScreen

@Composable
fun PlaceListScreen(
    navController: NavController,
    viewModel: PlaceListViewModel = hiltViewModel()
) {
    /* State */
    val isLoading by remember { viewModel.isLoading }
    val error by remember { viewModel.error }

    val selectedFilter by remember { viewModel.selectedFilter }
    val placeList by remember { viewModel.placeList }
    val filterList by remember { viewModel.filterList }

    /* UI */
    Column {
        TopAppBar(
            elevation = 0.dp,
            title = {
                Text("")
            },
            backgroundColor = Color.White,
            navigationIcon = {
                IconButton(
                    onClick = {
                        navController.popBackStack()
                    }
                ) {
                    Icon(Icons.Filled.ArrowBack, null)
                }
            }
        )

        if (isLoading) {
            // Loading
        } else if (filterList.isNotEmpty()) {
            // Success
            FilterScreen(
                filters = filterList,
                selectedFilterNum = selectedFilter,
                viewModel = viewModel
            )

            Spacer(modifier = Modifier.height(8.dp))

            Divider(modifier = Modifier.height(1.dp))

            PlaceScreen(
                places = placeList,
                modifier = Modifier.fillMaxSize()
            )
        } else {
            // Error

        }
    }
}
package kr.co.younhwan.eatjnu.presentation.place_list

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kr.co.younhwan.eatjnu.presentation.place_list.components.FilterScreen

@Composable
fun PlaceListScreen(
    navController: NavController,
    viewModel: PlaceListViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

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

        FilterScreen()
    }
}
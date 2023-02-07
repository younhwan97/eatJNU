package kr.co.younhwan.eatjnu.presentation.place_list

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kr.co.younhwan.eatjnu.presentation.home.HomeViewModel

@Composable
fun PlaceListScreen(
    navController: NavController,
    viewModel: PlaceListViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    Log.d("temp", state.toString())

}
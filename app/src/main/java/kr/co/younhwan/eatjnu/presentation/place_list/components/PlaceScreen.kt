package kr.co.younhwan.eatjnu.presentation.place_list.components

import android.annotation.SuppressLint
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kr.co.younhwan.eatjnu.domain.model.PlaceSummary
import kr.co.younhwan.eatjnu.presentation.Screen

@OptIn(ExperimentalAnimationApi::class)
@SuppressLint("ModifierParameter")
@Composable
fun PlaceScreen(
    userId: String,
    selectedFilter: String,
    places: List<PlaceSummary> = emptyList(),
    navController: NavController,
    modifier: Modifier = Modifier.fillMaxSize()
) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        modifier = modifier
    ) {
        items(items = places) { place ->
            if (place.id != -1) {

                val onClickPlace = { navController.navigate(Screen.PlaceDetailScreen.route + "/${place.id}" + "/${userId}") }

                if (selectedFilter == "전체") {
                    Place(info = place, onClickPlace = onClickPlace)
                } else {
                    if (selectedFilter == place.filter) {
                        Place(info = place, onClickPlace = onClickPlace)
                    }
                }
            }
        }
    }
}


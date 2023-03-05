package kr.co.younhwan.eatjnu.presentation.place_list.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kr.co.younhwan.eatjnu.domain.model.PlaceInfo
import kr.co.younhwan.eatjnu.presentation.Screen

@SuppressLint("ModifierParameter")
@Composable
fun PlaceScreen(
    navController: NavController,
    places: List<PlaceInfo> = emptyList(),
    selectedFilter: String,
    userId: String,
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
                    if (place.filter == selectedFilter) {
                        Place(info = place, onClickPlace = onClickPlace)
                    }
                }
            }
        }
    }
}
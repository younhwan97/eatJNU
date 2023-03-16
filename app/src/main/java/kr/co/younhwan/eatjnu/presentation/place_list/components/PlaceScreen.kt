package kr.co.younhwan.eatjnu.presentation.place_list.components

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

@Composable
fun PlaceScreen(
    userId: String,
    selectedFilter: String,
    places: List<PlaceSummary> = emptyList(),
    navController: NavController,
) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        items(places) { place ->
            if (place.id != -1) {
                val onClickPlace = { navController.navigate(Screen.PlaceDetailScreen.route + "/${place.id}" + "/${userId}") }

                if (selectedFilter == "전체") {
                    Place(placeSummary = place, onClickPlace = onClickPlace)
                } else if (selectedFilter == place.filter) {
                    Place(placeSummary = place, onClickPlace = onClickPlace)
                }
            }
        }
    }
}


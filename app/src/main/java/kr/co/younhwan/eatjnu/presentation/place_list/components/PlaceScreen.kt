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
    selectedFilterNum: Int,
    modifier: Modifier = Modifier.fillMaxSize()
) {

    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        modifier = modifier
    ) {

        items(items = places) { place ->

            if (place.id != -1) {
                val onClickPlace = { navController.navigate(Screen.PlaceDetailScreen.route + "/${place.id}") }

                when (selectedFilterNum) {
                    0 -> Place(info = place, onClickPlace = onClickPlace)
                    1 -> if (place.filter == "맛집") Place(info = place, onClickPlace = onClickPlace)
                    2 -> if (place.filter == "술집") Place(info = place, onClickPlace = onClickPlace)
                    3 -> if (place.filter == "카페") Place(info = place, onClickPlace = onClickPlace)
                }
            }
        }
    }
}
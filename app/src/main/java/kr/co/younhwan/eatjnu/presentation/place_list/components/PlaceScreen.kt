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

        items(items = places) { item ->

            val onClickPlace = { navController.navigate(Screen.PlaceDetailScreen.route + "/${item.id}") }

            when (selectedFilterNum) {
                0 -> Place(info = item, onClickPlace = onClickPlace)
                1 -> if (item.filter == "맛집") Place(info = item, onClickPlace = onClickPlace)
                2 -> if (item.filter == "술집") Place(info = item, onClickPlace = onClickPlace)
                3 -> if (item.filter == "카페") Place(info = item, onClickPlace = onClickPlace)
            }
        }
    }
}
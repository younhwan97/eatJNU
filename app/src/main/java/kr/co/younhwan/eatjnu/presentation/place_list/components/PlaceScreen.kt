package kr.co.younhwan.eatjnu.presentation.place_list.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kr.co.younhwan.eatjnu.domain.model.Place

@Composable
fun PlaceScreen(
    places: List<Place> = emptyList(),
    modifier: Modifier
) {
    LazyColumn() {
        items(items = places, itemContent = { item ->
            Place(info = item)
        })
    }
}
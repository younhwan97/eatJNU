package kr.co.younhwan.eatjnu.presentation.place_list.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kr.co.younhwan.eatjnu.domain.model.Place

@Composable
fun PlaceScreen(
    places: List<Place> = emptyList(),
    selectedFilterNum: Int,
    modifier: Modifier = Modifier.fillMaxSize()
) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp)
    ) {
        items(items = places, itemContent = { item ->
            if (selectedFilterNum == 0) {
                Place(info = item)
            } else if (selectedFilterNum == 1) {
                if (item.filter == "맛집")
                    Place(info = item)
            } else if (selectedFilterNum == 2) {
                if (item.filter == "술집")
                    Place(info = item)
            }
        })
    }
}
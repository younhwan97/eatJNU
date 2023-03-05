package kr.co.younhwan.eatjnu.presentation.place_list.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kr.co.younhwan.eatjnu.domain.model.FilterInfo
import kr.co.younhwan.eatjnu.presentation.place_list.PlaceListViewModel

@SuppressLint("ModifierParameter")
@Composable
fun FilterScreen(
    selectedFilter: String,
    filters: List<FilterInfo>,
    viewModel: PlaceListViewModel
) {
    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        items(filters) { item ->
            if (item.text == selectedFilter)
                Filter(info = item, selected = true) { viewModel.changeFilter(item.text) }
            else
                Filter(info = item, selected = false) { viewModel.changeFilter(item.text) }
        }
    }
}
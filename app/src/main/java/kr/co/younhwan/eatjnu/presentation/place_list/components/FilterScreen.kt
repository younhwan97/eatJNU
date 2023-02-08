package kr.co.younhwan.eatjnu.presentation.place_list.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kr.co.younhwan.eatjnu.domain.model.FilterInfo
import kr.co.younhwan.eatjnu.presentation.place_list.PlaceListViewModel

@SuppressLint("ModifierParameter")
@Composable
fun FilterScreen(
    filters: List<FilterInfo> = emptyList(),
    selectedFilterNum: Int,
    viewModel: PlaceListViewModel,
    modifier: Modifier = Modifier.fillMaxWidth()
) {

    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier,
    ) {

        itemsIndexed(items = filters) { index, item ->

            if (index == selectedFilterNum)
                Filter(info = item, selected = true, index = index) {
                    viewModel.changeFilter(it)
                }
            else
                Filter(info = item, index = index) {
                    viewModel.changeFilter(it)
                }
        }
    }
}
package kr.co.younhwan.eatjnu.presentation.place_list.components

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import kr.co.younhwan.eatjnu.domain.model.FilterInfo
import kr.co.younhwan.eatjnu.presentation.place_list.PlaceListViewModel

@Composable
fun FilterScreen(
    filters: List<FilterInfo> = emptyList(),
    selectedFilterNum: Int,
    viewModel: PlaceListViewModel,
    modifier: Modifier = Modifier.fillMaxWidth()
) {
    Log.d("temp", selectedFilterNum.toString())
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(
            start = 16.dp,
            end = 16.dp
        ),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        itemsIndexed(items = filters) { index, item ->
            if (index == selectedFilterNum)
                Filter(info = item, selected = true, index = index, onClickFilter = {
                    viewModel.changeFilter(it)
                })
            else
                Filter(info = item, selected = false, index = index, onClickFilter = {
                    viewModel.changeFilter(it)
                })
        }
    }
}
package kr.co.younhwan.eatjnu.presentation.place_list.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kr.co.younhwan.eatjnu.domain.model.FilterInfo

@Composable
fun FilterScreen(
    filters: List<FilterInfo> = emptyList(),
    selectedFilterNum: Int,
    modifier: Modifier = Modifier.fillMaxWidth()
) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(
            start = 16.dp,
            end = 16.dp
        ),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        itemsIndexed(items = filters, itemContent = { index, item ->
            if (index == selectedFilterNum)
                Filter(info = item, selected = true, onClickFilter = {})
            else
                Filter(info = item, onClickFilter = {})
        })
    }
}
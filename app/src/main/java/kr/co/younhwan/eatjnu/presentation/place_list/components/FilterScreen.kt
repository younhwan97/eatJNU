package kr.co.younhwan.eatjnu.presentation.place_list.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kr.co.younhwan.eatjnu.domain.model.Filter
import kr.co.younhwan.eatjnu.presentation.place_list.PlaceListViewModel

@Composable
fun FilterScreen(
    filters: List<Filter>,
    selectedFilter: String,
    viewModel: PlaceListViewModel
) {
    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        items(filters) { filter ->
            if (filter.text == selectedFilter) {
                // 선택된 필터를 다시 눌렀을 때 -> 어때한 이벤트도 호출되지 않음
                Filter(filter = filter, isSelected = true) { Unit }
            } else {
                // 선택되지 않은 필터를 눌렀을 때 -> 필터 변경 이벤트 호출
                Filter(filter = filter) { viewModel.changeFilter(filter.text) }
            }
        }
    }

    Spacer(modifier = Modifier.height(4.dp))
}
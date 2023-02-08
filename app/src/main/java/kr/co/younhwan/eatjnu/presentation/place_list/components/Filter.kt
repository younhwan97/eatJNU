package kr.co.younhwan.eatjnu.presentation.place_list.components

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kr.co.younhwan.eatjnu.domain.model.FilterInfo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Filter(
    info: FilterInfo,
    selected: Boolean = false,
    index: Int,
    onClickFilter: (Int) -> Unit
) {
    if (selected) {
        AssistChip(
            onClick = {
                onClickFilter(index)
            },
            label = {
                Text(
                    text = info.text,
                    modifier = Modifier.padding(vertical = 10.dp)
                )
            },
            colors = AssistChipDefaults.assistChipColors(
                containerColor = Color(0XFF28943E),
                labelColor = Color.White
            ),
            border = AssistChipDefaults.assistChipBorder(
                borderColor = Color.LightGray
            ),
            elevation = AssistChipDefaults.assistChipElevation(
                defaultElevation = 4.dp
            ),
            leadingIcon = {
                Icon(
                    painter = painterResource(id = info.resource),
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(AssistChipDefaults.IconSize)
                )
            }
        )
    } else {
        AssistChip(
            onClick = {
                onClickFilter(index)
            },
            label = {
                Text(
                    text = info.text,
                    modifier = Modifier.padding(vertical = 10.dp)
                )
            },
            colors = AssistChipDefaults.assistChipColors(
                containerColor = Color.White,
                labelColor = Color.Black
            ),
            border = AssistChipDefaults.assistChipBorder(
                borderColor = Color.LightGray
            ),
            elevation = AssistChipDefaults.assistChipElevation(
                defaultElevation = 4.dp
            ),
            leadingIcon = {
                Icon(
                    painter = painterResource(id = info.resource),
                    contentDescription = null,
                    tint = Color.Black,
                    modifier = Modifier.size(AssistChipDefaults.IconSize)
                )
            },
        )
    }
}
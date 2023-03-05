package kr.co.younhwan.eatjnu.presentation.place_list.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kr.co.younhwan.eatjnu.domain.model.FilterInfo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Filter(
    info: FilterInfo,
    selected: Boolean = false,
    onClickFilter: () -> Unit
) {
    // 필터 속성
    var tintColor = Color.Black // 아이콘, 글씨 색
    var containerColor = Color.White // 배경 색
    val borderColor = MaterialTheme.colors.secondary // 테두리 색
    var fontWeight = FontWeight.Normal // 글씨 두께

    // 선택된 필터 속성
    if (selected) {
        tintColor = Color.White
        containerColor = MaterialTheme.colors.primary
        fontWeight = FontWeight.Bold
    }

    // 필터
    AssistChip(
        onClick = onClickFilter,
        label = {
            Text(
                text = info.text,
                style = MaterialTheme.typography.body1,
                fontSize = 12.sp,
                fontWeight = fontWeight,
                modifier = Modifier.padding(vertical = 12.dp)
            )
        },
        colors = AssistChipDefaults.assistChipColors(
            containerColor = containerColor,
            labelColor = tintColor
        ),
        border = AssistChipDefaults.assistChipBorder(
            borderColor = borderColor
        ),
        elevation = AssistChipDefaults.assistChipElevation(
            defaultElevation = 4.dp
        ),
        leadingIcon = {
            Icon(
                painter = painterResource(id = info.resource),
                contentDescription = null,
                tint = tintColor,
                modifier = Modifier.size(AssistChipDefaults.IconSize)
            )
        }
    )
}
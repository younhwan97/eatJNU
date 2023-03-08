package kr.co.younhwan.eatjnu.presentation.place_list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kr.co.younhwan.eatjnu.R
import kr.co.younhwan.eatjnu.domain.model.FilterInfo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Filter(
    info: FilterInfo,
    selected: Boolean = false,
    onClickFilter: () -> Unit
) {
    AssistChip(
        onClick = onClickFilter,
        label = {
            Text(
                text = info.text,
                style = MaterialTheme.typography.body1,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = if (selected) Color.White else Color.Black,
                modifier = Modifier.padding(vertical = 12.dp)
            )
        },
        colors = AssistChipDefaults.assistChipColors(
            containerColor = if (selected) MaterialTheme.colors.primary else colorResource(id = R.color.BackgroundGray),
            labelColor = Color.Black
        ),
        border = AssistChipDefaults.assistChipBorder(
            borderColor = colorResource(id = R.color.BackgroundGray)
        ),
        elevation = AssistChipDefaults.assistChipElevation(
            defaultElevation = 0.dp
        ),
        leadingIcon = {
            Icon(
                painter = painterResource(id = info.resource),
                contentDescription = null,
                tint = Color.Black,
                modifier = Modifier
                    .size(AssistChipDefaults.IconSize)
                    .clip(CircleShape)
                    .background(if (selected) Color.White else colorResource(id = R.color.BackgroundGray))
                    .padding(1.dp)
            )
        },
        shape = RoundedCornerShape(24.dp)
    )
}
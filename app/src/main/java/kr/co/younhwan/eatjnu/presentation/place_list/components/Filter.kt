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
import androidx.compose.ui.unit.dp
import kr.co.younhwan.eatjnu.R
import kr.co.younhwan.eatjnu.domain.model.Filter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Filter(
    filter: Filter,
    isSelected: Boolean = false,
    onClickFilter: () -> Unit
) {
    AssistChip(
        onClick = onClickFilter,
        label = {
            Text(
                text = filter.text,
                style = MaterialTheme.typography.h6,
                color = if (isSelected) Color.White else Color.Black,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        },
        colors = AssistChipDefaults.assistChipColors(
            containerColor = if (isSelected) MaterialTheme.colors.primary else colorResource(id = R.color.BackgroundGray),
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
                painter = painterResource(id = filter.resource),
                contentDescription = null,
                tint = Color.Black,
                modifier = Modifier
                    .size(AssistChipDefaults.IconSize)
                    .clip(CircleShape)
                    .background(if (isSelected) Color.White else colorResource(id = R.color.BackgroundGray))
                    .padding(1.dp)
            )
        },
        shape = RoundedCornerShape(24.dp)
    )
}
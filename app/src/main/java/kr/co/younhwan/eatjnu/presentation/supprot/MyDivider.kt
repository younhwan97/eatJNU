package kr.co.younhwan.eatjnu.presentation.supprot

import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import kr.co.younhwan.eatjnu.R

@Composable
fun MyDivider() {
    Divider(
        thickness = 1.dp,
        color = colorResource(id = R.color.DividerGray)
    )

    Divider(
        thickness = 8.dp,
        color = colorResource(id = R.color.BackgroundGray)
    )
}
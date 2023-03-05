package kr.co.younhwan.eatjnu.presentation.supprot

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun MyDivider() {
    Divider(
        thickness = 1.dp,
        color = Color(0XFFDFDFDF)
    )

    Divider(
        thickness = 8.dp,
        color = Color(0XFFF4F4F4)
    )
}
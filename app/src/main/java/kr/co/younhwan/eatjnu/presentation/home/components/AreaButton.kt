package kr.co.younhwan.eatjnu.presentation.home.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kr.co.younhwan.eatjnu.domain.model.AreaInfo

@Composable
fun AreaButton(
    placeName: String,
    onAreaClick: () -> Unit
) {
    OutlinedButton(
        onClick = {
            onAreaClick()
        },
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(4.dp, Color.Black),
        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary),
        modifier = Modifier
            .fillMaxWidth()
            .height(54.dp)
    ) {
        Text(
            text = placeName,
            color = Color.White,
            letterSpacing = 10.sp,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.body1,
        )
    }
}
package kr.co.younhwan.eatjnu.presentation.place_detail.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun ReviewButton(
    onCLickBtn: () -> Unit
) {
    Button(
        onClick = onCLickBtn,
        shape = RoundedCornerShape(8.dp),
        contentPadding = PaddingValues(vertical = 8.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0XFF32BA4D)),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 48.dp)
    ) {
        Text(
            text = "리뷰 작성 & 모두 보기",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.body1
        )
    }
}
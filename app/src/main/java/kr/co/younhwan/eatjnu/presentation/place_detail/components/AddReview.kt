package kr.co.younhwan.eatjnu.presentation.place_detail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TextFieldDefaults.indicatorLine
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kr.co.younhwan.eatjnu.R

@Composable
fun AddReview(
    onClickEnterBtn: (String) -> Unit
) {
    var value by remember { mutableStateOf(TextFieldValue("")) }

    BasicTextField(
        value = value,
        onValueChange = {
            if (it.text.length <= 99) {
                value = it
            }
        },
        decorationBox = { innerTextField ->
            Row(
                Modifier
                    .background(Color(0XFFF4F4F4), RoundedCornerShape(percent = 30))
                    .padding(16.dp)
            ) {
                if (value.text.isEmpty()) {
                    Text(
                        text = "리뷰를 입력해주세요 :)",
                        color = Color.LightGray,
                        fontWeight = FontWeight.Normal,
                        fontSize = 14.sp,
                        style = MaterialTheme.typography.body1
                    )
                }
                Box(modifier = Modifier.weight(6f)) {
                    innerTextField()
                }

                IconButton(
                    onClick = {
                        onClickEnterBtn(value.text)
                        value = TextFieldValue("")
                    },
                    modifier = Modifier
                        .weight(1f)
                        .size(18.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Send,
                        contentDescription = null,

                        )
                }
            }
        },
        modifier = Modifier.fillMaxWidth()
    )

    Spacer(modifier = Modifier.height(8.dp))
}
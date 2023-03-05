package kr.co.younhwan.eatjnu.presentation.place_detail.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun ReviewDialog(
    onDismissRequest: () -> Unit = {}
) {
    Dialog(
        onDismissRequest = onDismissRequest
    ) {
        Surface(
            shape = RoundedCornerShape(12.dp),
            color = Color.White,
            contentColor = Color.Black
        ) {
            Column(
                modifier = Modifier.padding(16.dp).fillMaxWidth()
            ) {
                Text(
                    text = "닉네임",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Gray,
                    style = MaterialTheme.typography.body1
                )

                Spacer(modifier = Modifier.height(4.dp))

                var nameText by remember { mutableStateOf("익명") }

                BasicTextField(
                    value = nameText,
                    onValueChange = {
                        if (it.length <= 20) nameText = it
                    },
                    maxLines = 1,
                    textStyle = MaterialTheme.typography.body1,
                    modifier = Modifier
                        .border(
                            width = 1.dp,
                            shape = RoundedCornerShape(12.dp),
                            color = Color.LightGray
                        )
                        .fillMaxWidth()
                        .height(42.dp)
                        .padding(8.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "내용(최대 100자)",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Gray,
                    style = MaterialTheme.typography.body1
                )

                Spacer(modifier = Modifier.height(4.dp))

                var text by remember { mutableStateOf("") }

                BasicTextField(
                    value = text,
                    onValueChange = {
                        if (it.length <= 99) {
                            text = it
                        }
                    },
                    textStyle = MaterialTheme.typography.body1,
                    modifier = Modifier
                        .border(
                            width = 1.dp,
                            shape = RoundedCornerShape(12.dp),
                            color = Color.LightGray
                        )
                        .fillMaxWidth()
                        .height(102.dp)
                        .padding(8.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedButton(
                    onClick = {

                    },
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary),
                    enabled = text.length >= 5,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                ) {
                    Text(
                        text = "저장",
                        color = Color.White,
                        letterSpacing = 4.sp,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.body1,
                    )
                }

            }
        }
    }
}
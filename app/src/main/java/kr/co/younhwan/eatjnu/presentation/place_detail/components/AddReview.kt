package kr.co.younhwan.eatjnu.presentation.place_detail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AddReview(
    onClickEnterBtn: (String) -> Unit
) {
    var text by remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    BasicTextField(
        value = text,
        onValueChange = {
            if (it.length <= 99) {
                text = it
            }
        },
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(
            onDone = {
                onClickEnterBtn(text)
                keyboardController?.hide()
                text = ""
                focusManager.clearFocus()
            }
        ),
        decorationBox = { innerTextField ->
            Row(
                Modifier
                    .background(Color(0XFFF4F4F4), RoundedCornerShape(percent = 30))
                    .padding(16.dp)
                    .focusRequester(focusRequester)
            ) {
                if (text.isEmpty()) {
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
                        onClickEnterBtn(text)
                        keyboardController?.hide()
                        text = ""
                        focusManager.clearFocus()
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
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    )

    Spacer(modifier = Modifier.height(8.dp))
}
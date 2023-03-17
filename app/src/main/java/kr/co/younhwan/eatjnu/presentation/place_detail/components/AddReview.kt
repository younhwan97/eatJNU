package kr.co.younhwan.eatjnu.presentation.place_detail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import kr.co.younhwan.eatjnu.R
import kr.co.younhwan.eatjnu.presentation.place_detail.PlaceDetailViewModel

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AddReview(
    isUgcAgree: Boolean = false,
    alreadyWrittenReview: Boolean = false,
    onClickEnterBtn: (String) -> Unit,
    viewModel: PlaceDetailViewModel
) {
    var text by rememberSaveable { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current
    var isVisibleUgcDialog by remember { mutableStateOf(false) }

    if (isVisibleUgcDialog) {
        UgcDialog(
            onDismissRequest = {
                isVisibleUgcDialog = false
            },
            onClickEnterBtn = {
                viewModel.addUgcValue()
                onClickEnterBtn(text)
                keyboardController?.hide()
                text = ""
                focusManager.clearFocus()
            }
        )
    }

    BasicTextField(
        value = text,
        enabled = !alreadyWrittenReview,
        onValueChange = {
            if (it.length <= 99) {
                text = it
            }
        },
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(
            onDone = {
                if (text.isNotBlank() && text.length >= 5) {
                    if (isUgcAgree) {
                        onClickEnterBtn(text)
                        keyboardController?.hide()
                        text = ""
                        focusManager.clearFocus()
                    } else {
                        isVisibleUgcDialog = true
                    }
                }
            }
        ),
        decorationBox = { innerTextField ->
            Box(
                Modifier
                    .background(
                        color = colorResource(id = R.color.BackgroundGray),
                        shape = RoundedCornerShape(percent = 30)
                    )
                    .padding(16.dp)
                    .focusRequester(focusRequester)
            ) {
                if (text.isEmpty()) {
                    Text(
                        text = if (alreadyWrittenReview) "이미 리뷰를 입력하셨습니다!" else "리뷰를 입력해주세요 :)",
                        color = Color.LightGray,
                        style = MaterialTheme.typography.body1
                    )
                }
                innerTextField()

                if (alreadyWrittenReview || text.length < 5) {
                    IconButton(
                        enabled = false,
                        onClick = { },
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                            .size(18.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Send,
                            contentDescription = null,
                            tint = Color.LightGray
                        )
                    }
                } else {
                    IconButton(
                        onClick = {
                            if (text.isNotBlank() && text.length >= 5) {
                                if (isUgcAgree) {
                                    onClickEnterBtn(text)
                                    keyboardController?.hide()
                                    text = ""
                                    focusManager.clearFocus()
                                } else {
                                    isVisibleUgcDialog = true
                                }
                            }
                        },
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                            .size(18.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Send,
                            contentDescription = null,
                        )
                    }
                }
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    )

    Spacer(modifier = Modifier.height(8.dp))
}

@Composable
fun UgcDialog(
    onDismissRequest: () -> Unit,
    onClickEnterBtn: () -> Unit
) {
    Dialog(
        onDismissRequest = onDismissRequest
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            shape = RoundedCornerShape(4.dp),
            color = Color.White
        ) {
            UgcDialogContent(
                onDismissRequest = onDismissRequest,
                onClickEnterBtn = onClickEnterBtn
            )
        }
    }
}

@Composable
fun UgcDialogContent(
    onDismissRequest: () -> Unit,
    onClickEnterBtn: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(start = 16.dp)
                .padding(top = 8.dp, bottom = 4.dp)
        ) {
            Row(
                modifier = Modifier
                    .weight(6f)
            ) {
                Text(
                    text = "전대밥토끼",
                    color = MaterialTheme.colors.primary,
                    style = MaterialTheme.typography.h5,
                )

                Spacer(modifier = Modifier.width(2.dp))

                Text(
                    text = "댓글 이용약관",
                    style = MaterialTheme.typography.h5,
                )
            }

            IconButton(
                onClick = onDismissRequest,
                content = {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = null,
                        tint = colorResource(id = R.color.Gray)
                    )
                },
                modifier = Modifier.weight(1f)
            )
        }

        androidx.compose.material3.Divider(
            thickness = 1.dp,
            color = Color(0XFFD5D5D5),
        )

        Text(
            text = "다른 사용자에게 불쾌감을 주는 댓글 또는 무분별한 악성 댓글은 경고없이 삭제될 수 있습니다.",
            style = MaterialTheme.typography.body1,
            modifier = Modifier.padding(16.dp)
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 16.dp, bottom = 8.dp)
        ) {
            OutlinedButton(
                onClick = {
                    onClickEnterBtn()
                    onDismissRequest()
                },
                shape = RoundedCornerShape(size = 4.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary),
                modifier = Modifier.align(Alignment.CenterEnd)
            ) {
                androidx.compose.material.Text(
                    text = "동의",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.body1,
                )
            }
        }
    }
}
package kr.co.younhwan.eatjnu.presentation.place_detail.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.*
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kr.co.younhwan.eatjnu.R
import kr.co.younhwan.eatjnu.domain.model.Review

@Composable
fun ReviewItem(
    review: Review,
    onClickReportBtn: (Int) -> Unit
) {
    if (review.comment != "") { // 댓글 내용이 있는 경우
        val reviewImages = arrayListOf(
            painterResource(id = R.drawable.fox),
            painterResource(id = R.drawable.lion),
            painterResource(id = R.drawable.owl),
            painterResource(id = R.drawable.panda),
            painterResource(id = R.drawable.penguin),
            painterResource(id = R.drawable.rabbit),
            painterResource(id = R.drawable.whale)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Column {
                Spacer(modifier = Modifier.height(4.dp))
                // 댓글 이미지
                Image(
                    painter = reviewImages[(review.comment.length % reviewImages.size)],
                    contentDescription = null,
                    modifier = Modifier.size(32.dp)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column() {
                // 댓글 작성자
                Row() {
                    Text(
                        text = "익명",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.body1,
                        modifier = Modifier.weight(6f)
                    )

                    var showMenu by remember { mutableStateOf(false) }

                    Box(
                        modifier = Modifier.weight(1f)
                    ) {
                        Icon(
                            imageVector = Icons.Default.MoreVert,
                            contentDescription = null,
                            modifier = Modifier
                                .size(18.dp)
                                .clickable {
                                    showMenu = !showMenu
                                }
                                .align(Alignment.CenterEnd)
                            ,
                            tint = colorResource(id = R.color.Gray),
                        )

                        DropdownMenu(
                            expanded = showMenu,
                            onDismissRequest = {
                                showMenu = false
                            },
                        ) {
                            DropdownMenuItem(
                                onClick = {
                                    onClickReportBtn(review.reviewId)
                                    showMenu = false
                                }
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.outline_flag_24),
                                        contentDescription = null,
                                        modifier = Modifier.size(18.dp),
                                    )

                                    Spacer(modifier = Modifier.width(16.dp))

                                    Text(
                                        text = "신고",
                                        fontSize = 14.sp,
                                        style = MaterialTheme.typography.h6
                                    )
                                }
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                // 댓글 내용
                Text(
                    text = review.comment,
                    fontSize = 12.sp,
                    style = MaterialTheme.typography.body1
                )

                Spacer(modifier = Modifier.height(4.dp))

                // 작성일
                Text(
                    text = review.writingTime,
                    fontSize = 10.sp,
                    color = colorResource(id = R.color.LightGray),
                    style = MaterialTheme.typography.body1
                )
            }
        }

        androidx.compose.material3.Divider(
            thickness = 1.dp,
            color = Color(0XFFD5D5D5),
            modifier = Modifier.padding(horizontal = 16.dp)
        )
    }
}
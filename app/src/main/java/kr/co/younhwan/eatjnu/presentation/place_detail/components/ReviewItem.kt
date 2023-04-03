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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kr.co.younhwan.eatjnu.R
import kr.co.younhwan.eatjnu.domain.model.PlaceReview
import java.util.*

@Composable
fun ReviewItem(
    userId: String,
    placeReview: PlaceReview,
    onCLickDeleteBtn: (Int, String, Int) -> Unit,
    onClickReportBtn: (Int) -> Unit
) {
    if (placeReview.comment.isNotBlank()) { // 댓글 내용이 있는 경우
        val reviewImages = arrayListOf(
            painterResource(id = R.drawable.fox),
            painterResource(id = R.drawable.lion),
            painterResource(id = R.drawable.owl),
            painterResource(id = R.drawable.panda),
            painterResource(id = R.drawable.penguin),
            painterResource(id = R.drawable.rabbit),
            painterResource(id = R.drawable.whale),
            painterResource(id = R.drawable.squid),
            painterResource(id = R.drawable.walrus),
            painterResource(id = R.drawable.chicken),
            painterResource(id = R.drawable.pig)
        ) // 리뷰 아이콘
        var isVisibleMenu by remember { mutableStateOf(false) } // 드랍다운 메뉴가 보이는지

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Column {
                Spacer(modifier = Modifier.height(4.dp))
                // 댓글 이미지
                Image(
                    painter = reviewImages[(placeReview.comment.length % reviewImages.size)],
                    contentDescription = null,
                    modifier = Modifier.size(32.dp)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    // 작성자
                    Text(
                        text = "익명",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = if (userId == placeReview.userId) MaterialTheme.colors.primary else Color.Black,
                        style = MaterialTheme.typography.body1,
                        modifier = Modifier.weight(5f)
                    )

                    Row(
                        horizontalArrangement = Arrangement.End,
                    ) {
                        // 댓글 좋아요
//                        Icon(
//                            imageVector = Icons.Outlined.FavoriteBorder,
//                            contentDescription = null,
//                            modifier = Modifier.size(18.dp),
//                            tint = colorResource(id = R.color.Gray)
//                        )
//
//                        Spacer(modifier = Modifier.width(12.dp))

                        // 댓글 신고/차단 및 삭제 드랍다운 메뉴
                        Box {
                            Icon(
                                imageVector = Icons.Default.MoreVert,
                                contentDescription = null,
                                modifier = Modifier
                                    .size(18.dp)
                                    .clickable {
                                        isVisibleMenu = !isVisibleMenu
                                    }
                                    .align(Alignment.CenterEnd),
                                tint = colorResource(id = R.color.Gray),
                            )

                            DropdownMenu(
                                expanded = isVisibleMenu,
                                onDismissRequest = {
                                    isVisibleMenu = false
                                }
                            ) {
                                DropdownMenuItem(
                                    onClick = {
                                        if (placeReview.userId == userId) {
                                            // 자신이 작성한 리뷰일 때
                                            onCLickDeleteBtn(placeReview.reviewId, userId, placeReview.placeId)
                                        } else {
                                            // 자신이 작성한 리뷰가 아닐 때
                                            onClickReportBtn(placeReview.reviewId)
                                        }
                                        isVisibleMenu = false
                                    }
                                ) {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Icon(
                                            painter =
                                            if (placeReview.userId == userId) painterResource(id = R.drawable.baseline_delete_outline_24)
                                            else painterResource(id = R.drawable.outline_flag_24),
                                            contentDescription = null,
                                            modifier = Modifier.size(18.dp),
                                        )

                                        Spacer(modifier = Modifier.width(8.dp))

                                        Text(
                                            text = if (placeReview.userId == userId) "삭제" else "신고/차단",
                                            fontSize = 14.sp,
                                            style = MaterialTheme.typography.h6
                                        )
                                    }
                                }
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                // 댓글 내용
                Text(
                    text = placeReview.comment,
                    fontSize = 12.sp,
                    style = MaterialTheme.typography.body1
                )

                Spacer(modifier = Modifier.height(4.dp))

                // 작성일
                Text(
                    text =
                    if (placeReview.writingTime.length >= 8) {
                        val reviewWrittenYear = placeReview.writingTime.substring(0, 4)
                        val currentYear = Calendar.getInstance().get(Calendar.YEAR).toString()

                        if (reviewWrittenYear == currentYear) {
                            // 올 해 작성된 리뷰일 때
                            placeReview.writingTime.substring(5, placeReview.writingTime.length - 3)
                        } else {
                            // 올 해 작성된 리뷰가 아닐 때
                            placeReview.writingTime.substring(2, placeReview.writingTime.length - 3)
                        }
                    } else if (placeReview.writingTime == "지금") {
                        placeReview.writingTime
                    } else {
                        ""
                    },
                    fontSize = 10.sp,
                    color = colorResource(id = R.color.LightGray),
                    style = MaterialTheme.typography.body1
                )

                Spacer(modifier = Modifier.height(4.dp))
            }
        }

        androidx.compose.material3.Divider(
            thickness = 1.dp,
            color = Color(0XFFD5D5D5),
            modifier = Modifier.padding(horizontal = 16.dp)
        )
    }
}
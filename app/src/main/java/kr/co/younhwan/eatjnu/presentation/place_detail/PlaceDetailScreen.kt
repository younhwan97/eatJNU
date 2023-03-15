package kr.co.younhwan.eatjnu.presentation.place_detail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kr.co.younhwan.eatjnu.presentation.place_detail.components.*
import kr.co.younhwan.eatjnu.presentation.supprot.ErrorScreen
import kr.co.younhwan.eatjnu.presentation.supprot.LoadingScreen
import kr.co.younhwan.eatjnu.presentation.supprot.MyDivider
import kr.co.younhwan.eatjnu.presentation.supprot.MyTopAppBar
import kotlin.text.Typography.nbsp

@Composable
fun PlaceDetailScreen(
    navController: NavController,
    viewModel: PlaceDetailViewModel = hiltViewModel()
) {
    val error by remember { viewModel.error }
    val isLoading by remember { viewModel.isLoading }

    if (isLoading) {
        LoadingScreen()
    } else if (error.isNotEmpty()) {
        ErrorScreen()
    } else {
        val userId by remember { viewModel.userId } // 유저 식별값
        val detail by remember { viewModel.placeDetail } // 세부 정보
        val isLikePlace by remember { viewModel.isLikePlace } // 유저가 '좋아요'를 누른 장소인지

        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // 앱바
            MyTopAppBar(
                title = detail.name,
                navController = navController,
                isVisibleLikeBtn = true,
                isLikePlace = isLikePlace,
                onClickLikeBtn = {
                    if (isLikePlace) {
                        viewModel.removeLikePlace(placeId = detail.id)
                    } else {
                        viewModel.addLikePlace(placeId = detail.id)
                    }
                }
            )

            // 컨텐츠
            LazyColumn {
                // 이미지 슬라이드
                item {
                    ImageSlider(
                        image = detail.image,
                        images = detail.images
                    )
                }

                // 매장 정보
                item {
                    PlaceInfo(info = detail)
                    MyDivider()
                }

                // 리뷰 타이틀
                item {
                    Text(
                        text = "리뷰${nbsp}(" + detail.placeReviews.size + ")",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        style = androidx.compose.material.MaterialTheme.typography.body1,
                        modifier = Modifier.padding(16.dp)
                    )
                }

                // 리뷰 입력
                item {
                    var alreadyWrittenReview = false

                    for (review in detail.placeReviews) {
                        if (review.userId == userId) {
                            alreadyWrittenReview = true
                            break
                        }
                    }

                    AddReview(
                        alreadyWrittenReview = alreadyWrittenReview,
                        onClickEnterBtn = {
                            viewModel.createPlaceReview(
                                userId = userId,
                                placeId = detail.id.toString(),
                                comment = it
                            )
                        }
                    )

                    Spacer(modifier = Modifier.height(8.dp))
                }

                // 리뷰
                items(detail.placeReviews.size) { index ->
                    ReviewItem(
                        placeReview = detail.placeReviews[index],
                        onClickReportBtn = {
                            viewModel.addPlaceReviewReport(userId = userId, reviewId = it)
                        }
                    )
                }

                item {
                    Spacer(modifier = Modifier.height(16.dp))

                    MyDivider()
                }
            }
        }
    }
} 
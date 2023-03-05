package kr.co.younhwan.eatjnu.presentation.place_detail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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

@Composable
fun PlaceDetailScreen(
    navController: NavController,
    viewModel: PlaceDetailViewModel = hiltViewModel()
) {
    val error by remember { viewModel.error }
    val isLoading by remember { viewModel.isLoading }

    val placeDetail by remember { viewModel.placeDetail }
    val isLikePlace by remember { viewModel.isLikePlace }
    val userId by remember { viewModel.userId }

    if (isLoading) {
        LoadingScreen()
    } else if (error != "") {
        ErrorScreen()
    } else {
        Column(modifier = Modifier.fillMaxSize()) {
            MyTopAppBar(
                title = placeDetail.name,
                navController = navController,
                isVisibleReviewBtn = true,
                isVisibleLikeBtn = true,
                isLikePlace = isLikePlace,
                onClickReviewBtn = {

                },
                onClickLikeBtn = {
                    if (isLikePlace) {
                        viewModel.removeLikePlace(placeId = placeDetail.id)
                    } else {
                        viewModel.addLikePlace(placeId = placeDetail.id)
                    }
                }
            )

            LazyColumn {
                /************ 매장 이미지 ************/
                // 이미지 슬라이드
                item {
                    ImageSlider(
                        mainImage = placeDetail.image ?: "",
                        images = placeDetail.images
                    )
                }

                /************ 매장 정보 ************/
                // 매장 정보
                item {
                    PlaceInfo(
                        info = placeDetail
                    )
                    MyDivider()
                }

                /************ 리뷰 ************/
                // 리뷰 타이틀
                item {
                    Text(
                        text = "리뷰",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        style = androidx.compose.material.MaterialTheme.typography.body1,
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .padding(top = 16.dp, bottom = 4.dp)
                    )
                }
                // 상위 5개 리뷰
                items(placeDetail.reviews.size) { index ->
                    ReviewItem(review = placeDetail.reviews[index])

                    if (index + 1 != placeDetail.reviews.size) {
                        Divider(
                            thickness = 1.dp,
                            color = Color(0XFFD5D5D5),
                            modifier = Modifier.padding(horizontal = 16.dp)
                        )
                    }
                }
                // 리뷰 버튼
                item {
                    ReviewButton(onCLickBtn = {})
                    Spacer(modifier = Modifier.height(16.dp))
                    MyDivider()
                }
            }
        }
    }
} 
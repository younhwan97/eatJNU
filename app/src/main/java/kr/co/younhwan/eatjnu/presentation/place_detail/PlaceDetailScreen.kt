package kr.co.younhwan.eatjnu.presentation.place_detail

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.naver.maps.geometry.LatLng
import kr.co.younhwan.eatjnu.R
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

    if (isLoading) {
        LoadingScreen()
    } else if (error.isNotEmpty()) {
        ErrorScreen()
    } else {
        val userId by remember { viewModel.userId } // 유저 고유 식별값
        val placeDetail by remember { viewModel.placeDetail } // 장소 정보
        val isLikePlace by remember { viewModel.isLikePlace } // 유저가 좋아요를 누른 장소인지

        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // 앱바
            MyTopAppBar(
                title = placeDetail.name,
                navController = navController,
                isVisibleLikeBtn = true,
                isLikePlace = isLikePlace,
                onClickLikeBtn = {
                    if (isLikePlace) {
                        viewModel.removeLikePlace(placeId = placeDetail.id)
                    } else {
                        viewModel.addLikePlace(placeId = placeDetail.id)
                    }
                }
            )

            // 컨텐츠
            LazyColumn {
                // 이미지 슬라이드
                item {
                    ImageSlider(
                        image = placeDetail.image,
                        images = placeDetail.images
                    )
                }

                // 매장 정보
                item {
                    PlaceInfo(info = placeDetail)
                    MyDivider()
                }

                // 리뷰 타이틀
                item {
                    Text(
                        text = "리뷰",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        style = androidx.compose.material.MaterialTheme.typography.body1,
                        modifier = Modifier.padding(16.dp)
                    )
                }

                // 리뷰 입력
                item {
                    AddReview(
                        onClickEnterBtn = {
                            viewModel.createPlaceReview(
                                userId = userId,
                                placeId = placeDetail.id.toString(),
                                comment = it
                            )
                        }
                    )
                }

                // 리뷰
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

                item {
                    MyDivider()
                }
            }
        }
    }
} 
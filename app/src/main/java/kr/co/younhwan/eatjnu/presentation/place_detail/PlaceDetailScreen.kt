package kr.co.younhwan.eatjnu.presentation.place_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.naver.maps.geometry.LatLng
import kotlinx.coroutines.launch
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
                    ExpandableCard(
                        title = "리뷰",
                        titleWeight = FontWeight.Bold,
                        titleSize = 16.sp,
                        toggleButtonIsIcon = false,
                        toggleButtonText = "작성",
                        content = {
                            NaverMap(latLng = LatLng(placeDetail.lat, placeDetail.lon))
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
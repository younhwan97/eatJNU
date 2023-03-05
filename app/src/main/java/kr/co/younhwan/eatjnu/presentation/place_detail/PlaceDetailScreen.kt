package kr.co.younhwan.eatjnu.presentation.place_detail

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ContentAlpha
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kr.co.younhwan.eatjnu.domain.model.ReviewInfo
import kr.co.younhwan.eatjnu.presentation.place_detail.components.ImageSlider
import kr.co.younhwan.eatjnu.presentation.place_detail.components.PlaceInfo
import kr.co.younhwan.eatjnu.presentation.place_detail.components.PlaceReview
import kr.co.younhwan.eatjnu.presentation.place_detail.components.ReviewDialog
import kr.co.younhwan.eatjnu.presentation.supprot.ErrorScreen
import kr.co.younhwan.eatjnu.presentation.supprot.LoadingScreen
import kr.co.younhwan.eatjnu.presentation.supprot.MyTopAppBar

@Composable
fun PlaceDetailScreen(
    navController: NavController,
    viewModel: PlaceDetailViewModel = hiltViewModel()
) {
    /* State */
    val error by remember { viewModel.error }
    val isLoading by remember { viewModel.isLoading }

    val placeDetail by remember { viewModel.placeDetail }
    val isLikePlace by remember { viewModel.isLikePlace }

    var isVisibleReviewDialog by remember { mutableStateOf(false) }

    /* UI */
    Column(modifier = Modifier.fillMaxSize()) {
        if (isLoading) {
            // Loading
            LoadingScreen()
        } else if (placeDetail.id != -1) {
            // 앱 바
            MyTopAppBar(
                title = placeDetail.name,
                navController = navController,
                isVisibleReviewBtn = true,
                isVisibleLikeBtn = true,
                isLikePlace = isLikePlace,
                onClickReviewBtn = {
                    isVisibleReviewDialog = !isVisibleReviewDialog
                },
                onClickLikeBtn = {
                    if (isLikePlace) {
                        viewModel.removeLikePlace(placeId = placeDetail.id)
                    } else {
                        viewModel.addLikePlace(placeId = placeDetail.id)
                    }
                }
            )

            // 리뷰 작성 다이얼로그
            if (isVisibleReviewDialog) {
                ReviewDialog(
                    onDismissRequest = {
                        isVisibleReviewDialog = !isVisibleReviewDialog
                    }
                )
            }

            // 컨텐츠
            LazyColumn {
                item {
                    Divider(
                        thickness = (0.5).dp,
                        color = Color.LightGray
                    )

                    ImageSlider(
                        mainImage = placeDetail.image ?: "",
                        images = placeDetail.images
                    )

                    Divider(
                        thickness = (0.5).dp,
                        color = Color.LightGray
                    )

                    PlaceInfo(
                        info = placeDetail
                    )

                    Divider(
                        thickness = 8.dp,
                        color = Color(0XFFF2F4F6)
                    )
                }

                item {
                    Text(
                        text = "리뷰",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        style = androidx.compose.material.MaterialTheme.typography.body1,
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .padding(top = 8.dp)
                    )
                }

                items(placeDetail.reviews.size) { index ->
                    PlaceReview(review = placeDetail.reviews[index])
                }
            }
        } else {
            // Error
            ErrorScreen()
            Log.e("error", "Error on PlaceDetail: $error")
        }
    }
} 
package kr.co.younhwan.eatjnu.presentation.place_detail.components

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.google.accompanist.pager.*
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage
import kr.co.younhwan.eatjnu.R
import kr.co.younhwan.eatjnu.domain.model.PlaceImage

@SuppressLint("ModifierParameter")
@OptIn(ExperimentalPagerApi::class)
@Composable
fun ImageSlider(
    image: String? = null,
    images: List<PlaceImage> = emptyList(),
    modifier: Modifier = Modifier.fillMaxWidth()
) {
    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        if (images.isNotEmpty()) { // 이미지가 여러장 있는 경우
            // State
            val pagerState = rememberPagerState(initialPage = 0)

            // Images
            HorizontalPager(
                count = images.size,
                state = pagerState,
                contentPadding = PaddingValues(start = 0.dp, end = 0.dp)
            ) { index ->

                GlideImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(368.dp),
                    imageModel = { images[index].url },
                    imageOptions = ImageOptions(
                        contentScale = ContentScale.Crop,
                        alignment = Alignment.Center
                    ),
                    requestOptions = {
                        RequestOptions()
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                    },
                )
            }

            // Indicator
            ImageSliderIndicator(
                numberOfPages = pagerState.pageCount,
                selectedPage = pagerState.currentPage,
                defaultRadius = 8.dp,
                selectedLength = 16.dp,
                space = 8.dp,
                animationDurationInMillis = 500,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(8.dp)
            )
        } else if (!image.isNullOrBlank()) { // 메인 이미지만 있는 경우
            GlideImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(368.dp),
                imageModel = { image },
                imageOptions = ImageOptions(
                    contentScale = ContentScale.Crop,
                    alignment = Alignment.Center
                ),
                requestOptions = {
                    RequestOptions()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                },
            )
        } else { // 이미지가 하나도 없는 경우
            Image(
                painter = painterResource(R.drawable.image),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(368.dp),
            )
        }
    }
}


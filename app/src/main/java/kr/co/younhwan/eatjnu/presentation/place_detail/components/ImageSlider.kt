package kr.co.younhwan.eatjnu.presentation.place_detail.components

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.google.accompanist.pager.*
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage
import kr.co.younhwan.eatjnu.domain.model.FoodImageInfo

@SuppressLint("ModifierParameter")
@OptIn(ExperimentalPagerApi::class)
@Composable
fun ImageSlider(
    images: List<FoodImageInfo> = emptyList(),
    modifier: Modifier = Modifier.fillMaxWidth()
) {

    val pagerState = rememberPagerState(initialPage = 0)

    Box(modifier = Modifier.fillMaxWidth()) {

        if(images.isNotEmpty()){
            HorizontalPager(
                count = images.size,
                state = pagerState,
                contentPadding = PaddingValues(start = 0.dp, end = 0.dp),
                modifier = Modifier.background(color = Color.Black)
            ) { page ->

                GlideImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(286.dp),
                    imageModel = { images[page].url },
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

            PageIndicator(
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
        } else {

        }
    }
}

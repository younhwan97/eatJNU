package kr.co.younhwan.eatjnu.presentation.place_detail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage
import kr.co.younhwan.eatjnu.domain.model.FoodImageInfo
import kr.co.younhwan.eatjnu.presentation.place_detail.PlaceDetailViewModel

@Composable
fun ImageScreen(
    images: List<FoodImageInfo>,
    viewModel: PlaceDetailViewModel
) {
    val scrollState = rememberLazyListState()
    val sortedImages: MutableList<FoodImageInfo> = mutableListOf()

    for (i in images.indices) if (images[i].isMenu == 1) sortedImages.add(images[i])
    for (i in images.indices) if (images[i].isMenu == 0) sortedImages.add(images[i])

    LazyRow(
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        state = scrollState
    ) {
        items(items = sortedImages, itemContent = { item ->

            if (item.isMenu == 1) {
                // 메뉴 사진
                Box(
                    modifier = Modifier
                        .size(width = 130.dp, height = 130.dp)
                        .clickable {
                            viewModel.setModalImageUrl(item.url)
                        }
                ) {
                    GlideImage(
                        modifier = Modifier
                            .fillMaxSize(),
                        imageModel = { item.url }, // loading a network image using an URL.
                        imageOptions = ImageOptions(
                            contentScale = ContentScale.Crop,
                            alignment = Alignment.Center,
                        )
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.TopStart)
                            .background(Color.Black.copy(alpha = 0.3f))
                    ) {
                        Text(
                            text = "메뉴",
                            textAlign = TextAlign.Center,
                            fontSize = 12.sp,
                            color = Color.White,
                            style = MaterialTheme.typography.body1,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(2.dp)
                        )
                    }
                }
            } else {
                // 음식 사진
                GlideImage(
                    modifier = Modifier
                        .size(130.dp, 130.dp)
                        .clickable {
                            viewModel.setModalImageUrl(item.url)
                        },
                    imageModel = { item.url }, // loading a network image using an URL.
                    imageOptions = ImageOptions(
                        contentScale = ContentScale.Crop,
                        alignment = Alignment.Center,
                    )
                )
            }
        })
    }
}
package kr.co.younhwan.eatjnu.presentation.place_detail.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage
import kr.co.younhwan.eatjnu.R
import kr.co.younhwan.eatjnu.domain.model.PlaceDetailInfo
import kr.co.younhwan.eatjnu.presentation.supprot.MyDivider
import kr.co.younhwan.eatjnu.presentation.supprot.MyTopAppBar

@Composable
fun Header(
    placeDetail: PlaceDetailInfo
) {
    Box(
        modifier = Modifier
            .height(350.dp)
            .fillMaxWidth()
            .drawBehind {
                val borderSize = 2.dp.toPx()
                val borderColor = Color.Gray

                drawLine(
                    color = borderColor,
                    start = Offset(0f, size.height),
                    end = Offset(size.width, size.height),
                    strokeWidth = borderSize
                )

                drawLine(
                    color = borderColor,
                    start = Offset(0f, 0f),
                    end = Offset(size.width, 0f),
                    strokeWidth = borderSize
                )
            }
    ) {
        // 가게 이미지
        GlideImage(
            imageModel = {
                placeDetail.image
            },
            modifier = Modifier.fillMaxSize(),
            imageOptions = ImageOptions(
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center,
            )
        )

        // 가게 정보
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .zIndex(1.5f)
        ) {

            // 가게 이름
            Text(
                text = placeDetail.name,
                fontSize = 32.sp,
                color = Color.White,
                style = MaterialTheme.typography.h1
            )

            Spacer(modifier = Modifier.height(16.dp))

            // 좋아요, 리뷰
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {

                // 좋아요
                Image(
                    painter = painterResource(id = R.drawable.like32),
                    contentDescription = null,
                    modifier = Modifier.size(16.dp)
                )

                Spacer(modifier = Modifier.width(4.dp))

                Text(
                    text = placeDetail.likeCount.toString(),
                    fontSize = 16.sp,
                    color = Color.White,
                    style = MaterialTheme.typography.h1,
                )

                Text(
                    text = "|",
                    fontSize = 16.sp,
                    color = Color.White,
                    style = MaterialTheme.typography.h1,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )

                // 리뷰
                Image(
                    painter = painterResource(id = R.drawable.pencil32),
                    contentDescription = null,
                    modifier = Modifier.size(16.dp)
                )

                Spacer(modifier = Modifier.width(4.dp))

                Text(
                    text = placeDetail.reviewCount.toString(),
                    fontSize = 16.sp,
                    color = Color.White,
                    style = MaterialTheme.typography.h1,
                )
            }

            Spacer(modifier = Modifier.height(32.dp))
        }


        // 그림자 효과
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.Black.copy(alpha = 0.0f),
                            Color.Black.copy(alpha = 0.0f),
                            Color.Black.copy(alpha = 0.0f),
                            Color.Black.copy(alpha = 0.0f),
                            Color.Black.copy(alpha = 0.0f),
                            Color.Black.copy(alpha = 0.0f),
                            Color.Black.copy(alpha = 0.3f),
                            Color.Black.copy(alpha = 0.4f),
                            Color.Black.copy(alpha = 0.5f),
                            Color.Black.copy(alpha = 0.6f),
                        ),
                    )
                )
        )
    }
}
package kr.co.younhwan.eatjnu.presentation.place_list.components

import androidx.compose.foundation.Image
import kr.co.younhwan.eatjnu.R
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage
import kr.co.younhwan.eatjnu.domain.model.PlaceSummary
import androidx.compose.material.MaterialTheme
import com.skydoves.landscapist.animation.crossfade.CrossfadePlugin
import com.skydoves.landscapist.components.rememberImageComponent
import com.skydoves.landscapist.placeholder.placeholder.PlaceholderPlugin

@Composable
fun Place(
    placeSummary: PlaceSummary,
    onClickPlace: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClickPlace)
    ) {
        // 장소 이미지
        ImageBox(
            imageUrl = placeSummary.image
        )

        Spacer(Modifier.width(16.dp))

        // 장소 정보
        Column() {
            // 이름
            Text(
                text = placeSummary.name,
                style = MaterialTheme.typography.h5
            )

            Spacer(modifier = Modifier.height(4.dp))

            // 좋아요 & 리뷰
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.heart),
                    contentDescription = null,
                    modifier = Modifier.size(18.dp)
                )

                Spacer(modifier = Modifier.width(2.dp))

                Text(
                    text = placeSummary.likeCount.toString(),
                    style = MaterialTheme.typography.h6
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = "리뷰",
                    color = colorResource(id = R.color.DarkGray),
                    style = MaterialTheme.typography.body1
                )

                Spacer(modifier = Modifier.width(2.dp))

                Text(
                    text = placeSummary.reviewCount.toString(),
                    color = colorResource(id = R.color.DarkGray),
                    style = MaterialTheme.typography.body1
                )
            }

            Spacer(modifier = Modifier.height(6.dp))

            // 태그
            Text(
                text = placeSummary.tags,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.fillMaxWidth(0.96f),
                style = MaterialTheme.typography.body2,
            )
        }
    }

    Spacer(modifier = Modifier.height(24.dp))
}

@Composable
fun ImageBox(
    imageUrl: String,
    roundedDp: Dp = 22.dp
) {
    Box(
        modifier = Modifier
            .size(width = 78.dp, height = 80.dp)
            .clip(RoundedCornerShape(roundedDp))
            .border(
                width = 1.dp,
                color = Color.Black, RoundedCornerShape(roundedDp)
            )
    ) {
        GlideImage(
            imageModel = { imageUrl },
            imageOptions = ImageOptions(
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center,
                contentDescription = null,
                colorFilter = null,
                alpha = 1f,
                requestSize = IntSize(256, 256)
            ),
            component = rememberImageComponent {
                +PlaceholderPlugin.Loading(painterResource(id = R.drawable.main3))
                +PlaceholderPlugin.Failure(painterResource(id = R.drawable.main3))
                +CrossfadePlugin(
                    duration = 1500
                )
            },
            requestOptions = {
                RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL)
            }
        )
    }
}
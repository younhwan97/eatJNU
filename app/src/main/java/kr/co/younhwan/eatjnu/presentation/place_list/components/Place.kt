package kr.co.younhwan.eatjnu.presentation.place_list.components

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage
import kr.co.younhwan.eatjnu.domain.model.PlaceInfo

@SuppressLint("ModifierParameter")
@Composable
fun Place(
    info: PlaceInfo,
    onClickPlace: () -> Unit,
    modifier: Modifier = Modifier.fillMaxWidth()
) {

    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable(
                onClick = onClickPlace
            )
    ) {
        // 장소 이미지
        ImageBox(imageUrl = info.image)

        Spacer(Modifier.width(16.dp))

        // 장소 정보
        Column() {
            // 이름
            Text(
                text = info.name,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                style = androidx.compose.material.MaterialTheme.typography.body1
            )

            Spacer(modifier = Modifier.height(2.dp))

            // 좋아요 & 리뷰
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = kr.co.younhwan.eatjnu.R.drawable.like32),
                    contentDescription = null,
                    modifier = Modifier.size(14.dp)
                )

                Spacer(modifier = Modifier.width(2.dp))

                Text(
                    text = info.likeCount.toString(),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    style = androidx.compose.material.MaterialTheme.typography.h1
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = "리뷰",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Gray,
                    style = androidx.compose.material.MaterialTheme.typography.body1
                )

                Spacer(modifier = Modifier.width(2.dp))

                Text(
                    text = info.reviewCount.toString(),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Gray,
                    style = androidx.compose.material.MaterialTheme.typography.body1
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // 태그
            Text(
                text = info.tags,
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
                color = Color.LightGray,
                style = androidx.compose.material.MaterialTheme.typography.body1,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.fillMaxWidth(0.9f)
            )
        }
    }

    Spacer(modifier = Modifier.height(16.dp))
}

@Composable
fun ImageBox(
    imageUrl: String,
    roundedDp: Dp = 24.dp
) {
    Box(
        modifier = Modifier
            .size(width = 88.dp, height = 90.dp)
            .clip(RoundedCornerShape(roundedDp))
            .border(
                width = (1.15).dp,
                color = Color.Black, RoundedCornerShape(roundedDp)
            )
    ) {
        GlideImage(
            imageModel = { imageUrl }, // loading a network image using an URL.
            imageOptions = ImageOptions(
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center
            ),
            requestOptions = {
                RequestOptions()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
            }
        )
    }
}
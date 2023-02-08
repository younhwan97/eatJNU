package kr.co.younhwan.eatjnu.presentation.place_list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage
import kr.co.younhwan.eatjnu.domain.model.Place

@Composable
fun Place(
    info: Place,
    modifier: Modifier = Modifier
        .fillMaxWidth()
) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        ImageBox(
            imageUrl = info.image
        )

        Spacer(Modifier.width(16.dp))

        Column() {
            Text(
                text = info.name,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                style = androidx.compose.material.MaterialTheme.typography.body1
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = kr.co.younhwan.eatjnu.R.drawable.ic_baseline_thumb_up_24),
                    contentDescription = null,
                    modifier = Modifier.size(18.dp)
                )

                Spacer(modifier = Modifier.width(2.dp))

                Text(
                    text = info.likeCount.toString(),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    style = androidx.compose.material.MaterialTheme.typography.body1
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

            Text(
                text = info.tags,
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Gray,
                style = androidx.compose.material.MaterialTheme.typography.body1
            )
        }
    }
}

@Composable
fun ImageBox(
    imageUrl: String,
    roundedDp: Dp = 24.dp
) {
    Box(
        modifier = Modifier
            .size(88.dp)
            .clip(RoundedCornerShape(roundedDp))
            .border(1.dp, Color.Black, RoundedCornerShape(roundedDp))
    ) {
        GlideImage(
            imageModel = { imageUrl }, // loading a network image using an URL.
            imageOptions = ImageOptions(
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center
            ),
        )
    }
}

@Preview
@Composable
fun PreviewGreeting() {
    Place(
        info = Place(
            id = 1,
            name = "다원",
            image = "https://mblogthumb-phinf.pstatic.net/MjAxOTA1MjNfMjI3/MDAxNTU4NTg1ODg0OTU0.C5NtgR4-3O_oiGM0g22Apf5tBKc5W-ehhWoDGGFm2-kg.En5IL_pdwMYe26R2-4AFBZTrLEkSKFfD6d3I3v6hpygg.JPEG.95da_0/IMG_6114.jpg?type=w800",
            reviewCount = 100,
            likeCount = 100,
            tags = "#맛집 #돌비"
        )
    )
}
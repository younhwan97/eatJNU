package kr.co.younhwan.eatjnu.presentation.place_detail.components

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.naver.maps.geometry.LatLng
import kr.co.younhwan.eatjnu.R
import kr.co.younhwan.eatjnu.domain.model.PlaceDetail
import kr.co.younhwan.eatjnu.presentation.supprot.MyDivider

@Composable
fun PlaceInfo(
    info: PlaceDetail
) {
    Column {
        // 가게 이름
        Text(
            text = info.name,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = androidx.compose.material.MaterialTheme.typography.body1,
            modifier = Modifier.padding(16.dp)
        )

        // 좋아요 및 리뷰 개수
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.heart),
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )

            Spacer(modifier = Modifier.width(4.dp))

            Text(
                text = info.likeCount.toString(),
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Black,
                style = androidx.compose.material.MaterialTheme.typography.body1
            )
            
            Spacer(modifier = Modifier.width(2.dp))

            Text(
                text = "개",
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Black,
                style = androidx.compose.material.MaterialTheme.typography.body1
            )

            Spacer(modifier = Modifier.width(16.dp))

            Image(
                painter = painterResource(id = R.drawable.chat),
                contentDescription = null,
                modifier = Modifier.size(22.dp)
            )

            Spacer(modifier = Modifier.width(4.dp))

            Text(
                text = info.reviews.size.toString(),
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Black,
                style = androidx.compose.material.MaterialTheme.typography.body1
            )

            Spacer(modifier = Modifier.width(2.dp))

            Text(
                text = "개",
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Black,
                style = androidx.compose.material.MaterialTheme.typography.body1
            )
        }

        // 주소
        if (info.lat != 0.0 && info.lon != 0.0) { // 위도, 경도 값이 있는 경우
            ExpandableCard(
                title = info.location,
                titleWeight = FontWeight.Normal,
                titleSize = 14.sp,
                toggleButtonIsIcon = true,
                content = {
                    NaverMap(latLng = LatLng(info.lat, info.lon))
                }
            )
        } else { // 위도, 경도 값이 없는 경우
            Text(
                text = info.location,
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                style = androidx.compose.material.MaterialTheme.typography.body1,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp)
            )
        }

        MyDivider()

        Text(
            text = "매장 정보",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            style = androidx.compose.material.MaterialTheme.typography.body1,
            modifier = Modifier.padding(16.dp)
        )

        // 운영 시간
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.ic_outline_schedule_24),
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )

            Spacer(modifier = Modifier.width(8.dp))

            if (info.openingInfo.isEmpty()) { // 가게 운영시간이 없는 경우
                Text(
                    text = "운영시간이 제공되지 않습니다 :(",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Gray,
                    style = androidx.compose.material.MaterialTheme.typography.body1
                )
            } else { // 가게 운영시간이 있는 경우
                Text(
                    text = info.openingInfo.toString(),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black,
                    style = androidx.compose.material.MaterialTheme.typography.body1
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // 전화 번호
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.ic_outline_call_24),
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )

            Spacer(modifier = Modifier.width(8.dp))

            if (info.number.isEmpty() || info.number.length <= 3) { // 가게 전화번호가 없는 경우
                Text(
                    text = "전화번호가 제공되지 않습니다 :(",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Gray,
                    style = androidx.compose.material.MaterialTheme.typography.body1
                )
            } else { // 가게 전화번호가 있는 경우
                val ctx = LocalContext.current
                Text(
                    text = info.number,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black,
                    style = androidx.compose.ui.text.TextStyle(
                        textDecoration = TextDecoration.Underline,
                        fontFamily = FontFamily(
                            Font(R.font.pretendard_regular, FontWeight.Normal, FontStyle.Normal),
                        )
                    ),
                    modifier = Modifier.clickable {
                        val u = Uri.parse("tel:" + info.number)
                        val i = Intent(Intent.ACTION_DIAL, u)
                        ctx.startActivity(i)
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // 태그
        if (info.tags.isNotEmpty()) {
            Text(
                text = info.tags,
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Gray,
                style = androidx.compose.material.MaterialTheme.typography.body1,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}
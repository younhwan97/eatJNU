package kr.co.younhwan.eatjnu.presentation.place_detail.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.Divider
import androidx.compose.material.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kr.co.younhwan.eatjnu.R
import kr.co.younhwan.eatjnu.domain.model.PlaceDetailInfo

@Composable
fun PlaceInfo(
    info: PlaceDetailInfo
) {
    Column {
        Text(
            text = info.name,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            style = androidx.compose.material.MaterialTheme.typography.body1,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(top = 16.dp)
        )
        
        ExpandableCard(
            title = info.location,
            horizontalPadding = 16.dp
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.love),
                contentDescription = null,
                modifier = Modifier.size(14.dp)
            )

            Spacer(modifier = Modifier.width(4.dp))

            Text(
                text = info.likeCount.toString(),
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Black,
                style = androidx.compose.material.MaterialTheme.typography.body1
            )

            Spacer(modifier = Modifier.width(2.dp))

            Text(
                text = "개",
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Black,
                style = androidx.compose.material.MaterialTheme.typography.body1
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Divider(
            color = Color(0XFFF2F4F6),
            modifier = Modifier.height(8.dp)
        )

        Text(
            text = "매장 정보",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            style = androidx.compose.material.MaterialTheme.typography.body1,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(top = 16.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

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

            Text(
                text = info.openingInfo.toString(),
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Black,
                style = androidx.compose.material.MaterialTheme.typography.body1
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

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

            Text(
                text = info.number.toString(),
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Black,
                style = androidx.compose.material.MaterialTheme.typography.body1
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = info.tags.toString(),
            fontSize = 12.sp,
            fontWeight = FontWeight.Normal,
            color = Color.LightGray,
            style = androidx.compose.material.MaterialTheme.typography.body1,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Divider(
            color = Color(0XFFF2F4F6),
            modifier = Modifier.height(8.dp)
        )
    }
}
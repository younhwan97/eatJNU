package kr.co.younhwan.eatjnu.presentation.place_detail.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kr.co.younhwan.eatjnu.R
import kr.co.younhwan.eatjnu.domain.model.ReviewInfo
import java.time.format.DateTimeFormatter

@Composable
fun PlaceReview(
    review: ReviewInfo
) {
    val icons = arrayListOf<Painter>(
        painterResource(id = R.drawable.fox),
        painterResource(id = R.drawable.lion),
        painterResource(id = R.drawable.owl),
        painterResource(id = R.drawable.panda),
        painterResource(id = R.drawable.penguin),
        painterResource(id = R.drawable.rabbit),
        painterResource(id = R.drawable.whale),
    )

    val comment = review.comment ?: ""
    val time = review.writingTime

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .padding(vertical = 8.dp)
    ) {
        Column() {
            Spacer(modifier = Modifier.height(4.dp))

            Image(
                painter = icons[(comment.length % icons.size)],
                contentDescription = null,
                modifier = Modifier.size(32.dp)
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        Column() {
            Text(
                text = review.name ?: "익명",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.body1
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = comment,
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
                style = MaterialTheme.typography.body1
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = time.toString(),
                fontSize = 10.sp,
                color = Color.LightGray,
                fontWeight = FontWeight.Normal,
                style = MaterialTheme.typography.body1
            )
        }
    }

    Divider(
        thickness = 1.dp,
        color = Color(0XFFD5D5D5),
        modifier = Modifier.padding(horizontal = 16.dp)
    )
}
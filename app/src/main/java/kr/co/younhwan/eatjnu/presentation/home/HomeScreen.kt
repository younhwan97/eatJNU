package kr.co.younhwan.eatjnu.presentation.home

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.runtime.getValue
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kr.co.younhwan.eatjnu.R
import kr.co.younhwan.eatjnu.presentation.Screen
import kr.co.younhwan.eatjnu.presentation.home.components.AreaButtonScreen

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val areas by remember { viewModel.areas }
    val userId by remember { viewModel.userId }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // 피드백 버튼
        val ctx = LocalContext.current
        IconButton(
            onClick = {
                val i = Intent(Intent.ACTION_VIEW, Uri.parse("https://open.kakao.com/o/syHpz4cf"))
                ctx.startActivity(i)
            },
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(top = 8.dp, start = 4.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.outline_chat_bubble_outline_24),
                contentDescription = null
            )
        }

        // 좋아요 버튼
        IconButton(
            onClick = { navController.navigate(Screen.LikePlaceListScreen.route + "/${userId}") },
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 8.dp, end = 4.dp)
        ) {
            Icon(
                imageVector = Icons.Outlined.FavoriteBorder,
                contentDescription = null
            )
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(horizontal = 16.dp)
        ) {
            // 메인 이미지
            Image(
                painter = painterResource(R.drawable.image),
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            // 메인 타이틀
            Text(
                text = "전대 밥토끼",
                fontSize = 48.sp,
                style = MaterialTheme.typography.h1,
            )

            Spacer(modifier = Modifier.height(8.dp))

            // 장소 버튼
            AreaButtonScreen(
                areas = areas,
                userId = userId,
                navController = navController
            )

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}
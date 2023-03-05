package kr.co.younhwan.eatjnu.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kr.co.younhwan.eatjnu.R
import kr.co.younhwan.eatjnu.presentation.Screen
import kr.co.younhwan.eatjnu.presentation.home.components.AreaButton
import kr.co.younhwan.eatjnu.presentation.home.components.AreaButtonScreen

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val userId by remember { viewModel.userId }
    val areaList by remember { viewModel.areaList }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
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
                userId = userId,
                areaList = areaList,
                navController = navController,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}
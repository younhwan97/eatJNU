package kr.co.younhwan.eatjnu.presentation.supprot

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBar(
    title: String = "",
    navController: NavController,
    isVisibleLikeBtn: Boolean = false,
    isLikePlace: Boolean = false,
    onClickLikeBtn: () -> Unit = {}
) {

    var navigationEnabled by remember { mutableStateOf(true) }

    CenterAlignedTopAppBar(
        title = {
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.body1,
            )
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color.White
        ),
        navigationIcon = {
            IconButton(
                enabled = navigationEnabled,
                onClick = {
                    navigationEnabled = !navigationEnabled
                    navController.popBackStack()
                }
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = null,
                    tint = Color.Black
                )
            }
        },
        actions = {
            // 좋아요 버튼
            if (isVisibleLikeBtn) {
                IconButton(onClick = onClickLikeBtn) {
                    if (isLikePlace) { // 이미 좋아요를 누른 장소일 때
                        Icon(
                            imageVector = Icons.Filled.Favorite,
                            contentDescription = null,
                            tint = Color(0XFFF40028)
                        )
                    } else { // 아직 좋아요를 누르지 않은 장소일 때
                        Icon(
                            imageVector = Icons.Outlined.FavoriteBorder,
                            contentDescription = null
                        )
                    }
                }
            }
        }
    )
}
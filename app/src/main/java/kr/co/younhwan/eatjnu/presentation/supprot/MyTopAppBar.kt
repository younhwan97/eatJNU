package kr.co.younhwan.eatjnu.presentation.supprot

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import kr.co.younhwan.eatjnu.presentation.Screen

@SuppressLint("ModifierParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBar(
    title: String = "",
    containerColor: Color = Color.White,
    navController: NavController,
    isVisibleHomeBtn: Boolean = false,
    isVisibleReviewBtn: Boolean = false,
    onClickReviewBtn: () -> Unit = {},
    isVisibleLikeBtn: Boolean = false,
    isLikePlace: Boolean = false,
    onClickLikeBtn: () -> Unit = {},
    modifier: Modifier = Modifier
) {

    CenterAlignedTopAppBar(
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.body1,
                fontWeight = FontWeight.Bold
            )
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = containerColor
        ),
        navigationIcon = {
            IconButton(
                onClick = {
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
            // 홈 버튼
            if (isVisibleHomeBtn) {
                IconButton(onClick = {
                    navController.navigate(Screen.HomeScreen.route) {
                        popUpTo(0)
                    }
                }) {
                    Icon(
                        imageVector = Icons.Filled.Home,
                        contentDescription = null,
                        tint = Color.Black
                    )
                }
            }

            // 리뷰 버튼
            if (isVisibleReviewBtn) {
                IconButton(onClick = onClickReviewBtn) {
                    Icon(
                        imageVector = Icons.Outlined.Edit,
                        contentDescription = null
                    )
                }
            }

            // 좋아요 버튼
            if (isVisibleLikeBtn) {
                IconButton(onClick = onClickLikeBtn) {
                    if (isLikePlace) {
                        Icon(
                            imageVector = Icons.Filled.Favorite,
                            contentDescription = null,
                            tint = Color(0XFFF40028)
                        )
                    } else {
                        Icon(
                            imageVector = Icons.Outlined.FavoriteBorder,
                            contentDescription = null
                        )
                    }
                }
            }
        },
        modifier = modifier
    )
}
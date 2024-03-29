package kr.co.younhwan.eatjnu.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint
import kr.co.younhwan.eatjnu.presentation.home.HomeScreen
import kr.co.younhwan.eatjnu.presentation.place_detail.PlaceDetailScreen
import kr.co.younhwan.eatjnu.presentation.like_place_list.LikePlaceListScreen
import kr.co.younhwan.eatjnu.presentation.place_list.PlaceListScreen
import kr.co.younhwan.eatjnu.presentation.ui.theme.EatJNUTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EatJNUTheme {
                // Status bar
                val systemUiController = rememberSystemUiController()
                SideEffect {
                    systemUiController.setStatusBarColor(
                        darkIcons = true,
                        color = Color.White
                    )
                }

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = Screen.HomeScreen.route
                    ) {
                        // 메인 화면
                        composable(
                            route = Screen.HomeScreen.route,
                        ) {
                            HomeScreen(navController)
                        }

                        // '좋아요'를 누른 장소 목록 화면
                        composable(
                            route = Screen.LikePlaceListScreen.route + "/{userId}"
                        ) {
                            LikePlaceListScreen(navController)
                        }

                        // (후문, 정문, 상대) 지역에 포함된 장소 목록 화면
                        composable(
                            route = Screen.PlaceListScreen.route + "/{areaType}" + "/{userId}"
                        ) {
                            PlaceListScreen(navController)
                        }

                        // 장소 세부 정보 화면
                        composable(
                            route = Screen.PlaceDetailScreen.route + "/{placeId}" + "/{userId}"
                        ) {
                            PlaceDetailScreen(navController)
                        }
                    }
                }
            }
        }
    }
}
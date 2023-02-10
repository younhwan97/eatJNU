package kr.co.younhwan.eatjnu.presentation

sealed class Screen(val route: String) {
    object HomeScreen : Screen("Home_screen")
    object PlaceListScreen : Screen("Place_list_screen")
    object PlaceDetailScreen : Screen("Place_detail_screen")
}
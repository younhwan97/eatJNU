package kr.co.younhwan.eatjnu.presentation.home.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kr.co.younhwan.eatjnu.domain.model.Area
import kr.co.younhwan.eatjnu.presentation.Screen

@Composable
fun AreaButtonScreen(
    userId: String,
    areas: List<Area>,
    navController: NavController
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp)
    ) {
        areas.forEach { info ->
            AreaButton(
                title = info.title,
                onClickBtn = {
                    navController.navigate(Screen.PlaceListScreen.route + "/${info.type}" + "/${userId}")
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
package kr.co.younhwan.eatjnu.presentation.home.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kr.co.younhwan.eatjnu.domain.model.AreaInfo
import kr.co.younhwan.eatjnu.presentation.Screen

@SuppressLint("ModifierParameter")
@Composable
fun AreaButtonScreen(
    areaList: List<AreaInfo>,
    navController: NavController,
    userId: String,
    modifier: Modifier = Modifier.fillMaxWidth()
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {

        // 장소 버튼 생성
        areaList.forEach { info ->

            AreaButton(
                placeName = info.title,
                onAreaClick = {
                    navController.navigate(Screen.PlaceListScreen.route + "/${info.type}" + "/${userId}")
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
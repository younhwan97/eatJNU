package kr.co.younhwan.eatjnu.presentation.place_list.components

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kr.co.younhwan.eatjnu.domain.model.PlaceInfo
import kr.co.younhwan.eatjnu.presentation.Screen
import soup.compose.material.motion.MaterialMotion
import soup.compose.material.motion.animation.materialFadeThrough

@OptIn(ExperimentalAnimationApi::class)
@SuppressLint("ModifierParameter")
@Composable
fun PlaceScreen(
    userId: String,
    selectedFilter: String,
    places: List<PlaceInfo> = emptyList(),
    navController: NavController,
    modifier: Modifier = Modifier.fillMaxSize()
) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        modifier = modifier
    ) {
        items(items = places) { place ->
            if (place.id != -1) {

                val onClickPlace = { navController.navigate(Screen.PlaceDetailScreen.route + "/${place.id}" + "/${userId}") }

                if (selectedFilter == "전체") {
                    Place(info = place, onClickPlace = onClickPlace)
                } else {
                    if (selectedFilter == place.filter) {
                        Place(info = place, onClickPlace = onClickPlace)
                    }
                }
            }
        }
    }
}


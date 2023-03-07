package kr.co.younhwan.eatjnu.presentation.place_detail.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraPosition
import com.naver.maps.map.compose.*

@OptIn(ExperimentalNaverMapApi::class)
@Composable
fun NaverMap(
    latLng: LatLng,
) {
    var mapProperties by remember {
        mutableStateOf(
            MapProperties(
                maxZoom = 18.0,
                minZoom = 12.0,
                symbolScale = 1f,
            )
        )
    }

    var mapUiSettings by remember {
        mutableStateOf(
            MapUiSettings(
                isCompassEnabled = false, // 나침반
                isZoomGesturesEnabled = false, // 줌
                isLocationButtonEnabled = false, // 현재 위치
                isScrollGesturesEnabled = false, // 스크롤
                isRotateGesturesEnabled = false, // 회전
                isTiltGesturesEnabled = false, // 각도

            )
        )
    }

    val cameraPositionState: CameraPositionState = rememberCameraPositionState {
        // 카메라 초기 위치를 설정합니다.
        position = CameraPosition(latLng, 15.0)
    }
    Box(
        Modifier.fillMaxWidth().height(160.dp)
    ) {
        com.naver.maps.map.compose.NaverMap(
            cameraPositionState = cameraPositionState,
            uiSettings = mapUiSettings,
            properties = mapProperties,
        )
    }

    Spacer(modifier = Modifier.height(8.dp))
}
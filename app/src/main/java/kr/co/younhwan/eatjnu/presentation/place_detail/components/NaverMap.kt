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
import com.naver.maps.map.overlay.OverlayImage
import kr.co.younhwan.eatjnu.R

@OptIn(ExperimentalNaverMapApi::class)
@Composable
fun NaverMap(
    latLng: LatLng,
) {
    val mapProperties by remember {
        mutableStateOf(
            MapProperties(
                maxZoom = 18.0,
                minZoom = 12.0,
                symbolScale = 1f,
            )
        )
    }

    val mapUiSettings by remember {
        mutableStateOf(
            MapUiSettings(
                isCompassEnabled = false, // 나침반
                isZoomGesturesEnabled = false, // 줌
                isLocationButtonEnabled = false, // 현재 위치
                isScrollGesturesEnabled = false, // 스크롤
                isRotateGesturesEnabled = false, // 회전
                isTiltGesturesEnabled = false, // 각도
                isLogoClickEnabled = false, // 네이버 로고 클릭 가능 여부
            )
        )
    }

    val cameraPositionState: CameraPositionState = rememberCameraPositionState {
        // 카메라 초기 위치를 설정합니다.
        position = CameraPosition(latLng, 15.0)
    }
    Box(
        Modifier
            .fillMaxWidth()
            .height(160.dp)
    ) {
        NaverMap(
            cameraPositionState = cameraPositionState,
            uiSettings = mapUiSettings,
            properties = mapProperties,
        ) {
            Marker(
                state = MarkerState(position = latLng),
                captionText = "",
                width = 24.dp,
                height = 32.dp,
                //icon = OverlayImage.fromResource(R.drawable.main3)
            )
        }
    }

    Spacer(modifier = Modifier.height(8.dp))
}
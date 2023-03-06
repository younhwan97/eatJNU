package kr.co.younhwan.eatjnu.presentation.place_detail.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.material.ContentAlpha
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraPosition
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.compose.*
import kr.co.younhwan.eatjnu.presentation.ui.theme.Shapes

@OptIn(ExperimentalMaterial3Api::class, ExperimentalNaverMapApi::class)
@Composable
fun ExpandableCard(
    title: String,
    lat: Double,
    lon: Double,
    horizontalPadding: Dp = 16.dp
) {
    var expandableState by remember { mutableStateOf(false) }
    val rotateState by animateFloatAsState(targetValue = if (expandableState) 180f else 0f)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = horizontalPadding)
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = 300,
                    easing = LinearOutSlowInEasing
                )
            ),
        shape = Shapes.medium,
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        onClick = {
            expandableState = !expandableState
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    modifier = Modifier.weight(6f),
                    text = title,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = androidx.compose.material.MaterialTheme.typography.body1
                )

                IconButton(
                    modifier = Modifier
                        .alpha(ContentAlpha.medium)
                        .rotate(rotateState)
                        .padding(horizontal = 0.dp)
                        .weight(1f),
                    onClick = {
                        expandableState = !expandableState
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = null
                    )
                }
            }

            if (expandableState) {
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
                            isLocationButtonEnabled = false, // 현재위치
                            isScrollGesturesEnabled = false, // 스크롤
                            isRotateGesturesEnabled = false, // 회전
                            isTiltGesturesEnabled = false, // 각도

                        )
                    )
                }

                val cameraPositionState: CameraPositionState = rememberCameraPositionState {
                    // 카메라 초기 위치를 설정합니다.
                    position = CameraPosition(LatLng(lat, lon), 15.0)
                }
                Box(
                    Modifier.fillMaxWidth().height(160.dp).padding(bottom = 16.dp)
                ) {
                    NaverMap(
                        cameraPositionState = cameraPositionState,
                        uiSettings = mapUiSettings,
                        properties = mapProperties,
                    )
                }
            }
        }
    }
}


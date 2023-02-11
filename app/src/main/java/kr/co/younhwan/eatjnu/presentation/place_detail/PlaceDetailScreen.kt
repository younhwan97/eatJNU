package kr.co.younhwan.eatjnu.presentation.place_detail

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kr.co.younhwan.eatjnu.presentation.place_detail.components.Header
import kr.co.younhwan.eatjnu.presentation.place_detail.components.ImageModal
import kr.co.younhwan.eatjnu.presentation.place_detail.components.ImageScreen
import kr.co.younhwan.eatjnu.presentation.supprot.ErrorScreen
import kr.co.younhwan.eatjnu.presentation.supprot.LoadingScreen
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView

@Composable
fun PlaceDetailScreen(
    navController: NavController,
    viewModel: PlaceDetailViewModel = hiltViewModel()
) {
    /* State */
    val error by remember { viewModel.error }
    val isLoading by remember { viewModel.isLoading }

    val placeDetail by remember { viewModel.placeDetail }
    val modalImageUrl by remember { viewModel.modalImageUrl }

    /* UI */
    Column {
        if (isLoading) {
            // Loading
            LoadingScreen()
        } else if (placeDetail.id != -1) {
            // 좋아요, 리뷰 및 가게 메인 사진
            Header(
                placeDetail = placeDetail,
                navController = navController
            )

            Divider(
                color = Color(0XFFF4F4F4),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp)
            )

            if (placeDetail.images.isNotEmpty()) {
                // 음식 사진 리스트
                ImageScreen(images = placeDetail.images, viewModel = viewModel)

                // 음식 사진 모달
                if (modalImageUrl != "")
                    ImageModal(url = modalImageUrl, viewModel = viewModel)

                Divider(
                    color = Color(0XFFF4F4F4),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(8.dp)
                )
            }

            Column(modifier = Modifier.padding(16.dp)) {
                if (placeDetail.location.isNotBlank()) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Filled.LocationOn,
                            contentDescription = null,
                            tint = Color.Gray,
                            modifier = Modifier.size(16.dp)
                        )

                        Spacer(modifier = Modifier.width(4.dp))

                        Text(
                            text = placeDetail.location,
                            style = MaterialTheme.typography.body1,
                            fontSize = 14.sp,
                            color = Color.Gray
                        )
                    }

                    if (placeDetail.lat != 0.0 && placeDetail.lon != 0.0) {
                        AndroidView(
                            factory = {
                                MapView(it)
                            },
                            update = {
                                // 중심점 변경 + 줌 레벨 변경
                                it.setMapCenterPointAndZoomLevel(MapPoint.mapPointWithGeoCoord(placeDetail.lat, placeDetail.lon), 2, false);

                                // 마커 생성
                                val marker = MapPOIItem()
                                marker.itemName = placeDetail.name
                                marker.tag = 0
                                marker.markerType = MapPOIItem.MarkerType.CustomImage
                                marker.customImageResourceId = kr.co.younhwan.eatjnu.R.drawable.rabbit64
                                marker.mapPoint = MapPoint.mapPointWithGeoCoord(placeDetail.lat, placeDetail.lon)
                                it.addPOIItem(marker)
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(168.dp)
                                .padding(top = 8.dp)
                        )
                    }
                }
            }


        } else {
            // Error
            ErrorScreen()
            Log.e("error", "Error on PlaceDetail: $error")
        }
    }
}
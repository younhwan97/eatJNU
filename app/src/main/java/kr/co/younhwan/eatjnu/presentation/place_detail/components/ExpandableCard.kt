package kr.co.younhwan.eatjnu.presentation.place_detail.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.ContentAlpha
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraPosition
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.compose.*
import kr.co.younhwan.eatjnu.presentation.ui.theme.Shapes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpandableCard(
    title: String,
    titleWeight: FontWeight = FontWeight.Normal,
    titleSize: TextUnit = 14.sp,
    toggleButtonIsIcon: Boolean = true,
    toggleButtonText: String = "",
    isExpanded: Boolean = false,
    content: @Composable() () -> Unit,
    horizontalPadding: Dp = 16.dp
) {
    var expandableState by rememberSaveable { mutableStateOf(isExpanded) }
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
                    fontSize = titleSize,
                    fontWeight = titleWeight,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = androidx.compose.material.MaterialTheme.typography.body1
                )

                if (toggleButtonIsIcon) {
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
                            contentDescription = null,
                        )
                    }
                } else {
                    Text(
                        modifier = Modifier
                            .weight(1f)
                            .alpha(ContentAlpha.medium)
                            .padding(vertical = 16.dp)
                            .clickable {
                                expandableState = !expandableState
                            },
                        text = toggleButtonText,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.DarkGray,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = androidx.compose.material.MaterialTheme.typography.body1,
                        textAlign = TextAlign.Right
                    )
                }
            }

            if (expandableState) {
                content()
            }
        }
    }
}


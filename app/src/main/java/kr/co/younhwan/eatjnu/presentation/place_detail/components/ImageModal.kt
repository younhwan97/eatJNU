package kr.co.younhwan.eatjnu.presentation.place_detail.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage
import kr.co.younhwan.eatjnu.presentation.place_detail.PlaceDetailViewModel

@Composable
fun ImageModal(
    url: String,
    viewModel: PlaceDetailViewModel
) {
    Dialog(
        onDismissRequest = { viewModel.setModalImageUrl("") }
    ) {
        Box {
            GlideImage(
                imageModel = { url }, // loading a network image using an URL.
                imageOptions = ImageOptions(
                    contentScale = ContentScale.Crop,
                    alignment = Alignment.Center,
                ),
                modifier = Modifier
                    .size(366.dp),
            )
        }
    }
}
package kr.co.younhwan.eatjnu.presentation.supprot

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import com.airbnb.lottie.compose.*
import kr.co.younhwan.eatjnu.R

@Composable
fun LoadingScreen() {
    val composition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.loading)
    )
    val lottieAnimatable = rememberLottieAnimatable()

    LaunchedEffect(composition) {
        lottieAnimatable.animate(
            composition = composition,
            clipSpec = LottieClipSpec.Frame(0, 1200),
            initialProgress = 0f,
            iterations = LottieConstants.IterateForever
        )
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        LottieAnimation(
            composition = composition,
            progress = lottieAnimatable.progress,
            contentScale = ContentScale.FillHeight,
            modifier = Modifier.align(Alignment.Center),
        )
    }
}
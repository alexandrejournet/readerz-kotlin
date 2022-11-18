package com.zakin.readerzmultiplatform.android.presentation.ui.screens

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import com.zakin.readerzmultiplatform.android.R
import com.zakin.readerzmultiplatform.android.presentation.ui.theme.textColorDark
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(goBack: () -> Unit) {
    val scale = remember {
        androidx.compose.animation.core.Animatable(0f)
    }

    val image: Painter = painterResource(id = R.drawable.ic_launcher_round)

    // AnimationEffect
    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 3f,
            animationSpec = tween(
                durationMillis = 800,
                easing = {
                    OvershootInterpolator(4f).getInterpolation(it)
                })
        )
        delay(3000L)
        goBack()
    }

    // Image
    Column(modifier = Modifier
        .fillMaxSize().background(MaterialTheme.colors.primaryVariant),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally) {
        Image(image,
            contentDescription = "Logo",
            modifier = Modifier.scale(scale.value))
    }
}
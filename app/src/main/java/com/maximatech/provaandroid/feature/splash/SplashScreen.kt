package com.maximatech.provaandroid.feature.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.maximatech.provaandroid.R
import com.maximatech.provaandroid.app.theme.SplashGradientEnd
import com.maximatech.provaandroid.app.theme.SplashGradientStart
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    onSplashTimeout: () -> Unit
) {
    LaunchedEffect(Unit) {
        delay(3000)
        onSplashTimeout()
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        SplashGradientStart,
                        SplashGradientEnd
                    )
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.maxima_logotipo),
            contentDescription = "Maxima LogoTipo",
            modifier = Modifier.size(150.dp)
        )
    }
}
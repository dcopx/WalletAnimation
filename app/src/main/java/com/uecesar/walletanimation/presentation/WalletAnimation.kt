package com.uecesar.walletanimation.presentation

import android.app.Activity
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.spring
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowInsetsControllerCompat
import com.uecesar.walletanimation.presentation.components.SharedProfileAvatar
import com.uecesar.walletanimation.presentation.ui.theme.WalletAnimationTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.math.roundToInt
import kotlin.math.sqrt

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WalletAnimation() {
    val density = LocalDensity.current
    val config = LocalWindowInfo.current
    val scope = rememberCoroutineScope()
    val statusBarPadding = WindowInsets.statusBars.asPaddingValues().calculateTopPadding()
    val screenHeightDp = config.containerSize.height.dp
    val screenWidthDp = config.containerSize.width.dp - 400.dp

    val rollTargetPx = with(density) { (screenHeightDp * 0.3f).toPx() } //usa density para convertir dp a pixeles

    val screenRollOffset = remember { Animatable(0f) }
    var islandExpansionProgress by remember { mutableFloatStateOf(0f) }
    var isProfileOpen by remember { mutableStateOf(false) }
    val progress by remember {
        derivedStateOf { (screenRollOffset.value / rollTargetPx).coerceIn(0f, 1f) }
    }

    StatusBarColor()

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 20.dp)
    ) { paddingValues ->
        Box(
            Modifier
                .padding(paddingValues)
                .padding(top = statusBarPadding)
        ) {
            val avatarAlpha = (1f - (islandExpansionProgress * 6f)).coerceIn(0f, 1f)

            ProfileScreen( progress = progress )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .offset { IntOffset(0, screenRollOffset.value.roundToInt()) }
            ) {
                HomeScreen(
                    onProfileClick = {
                        toggleProfile(
                            scope,
                            isProfileOpen,
                            screenRollOffset,
                            rollTargetPx
                        ) { isProfileOpen = it }
                    },
                    progress = progress,
                    onIslandProgress = { islandExpansionProgress = it }
                )
            }

            if (avatarAlpha > 0f) {
                SharedProfileAvatar(
                    progress = progress,
                    screenWidth = screenWidthDp,
                    alpha = avatarAlpha,
                    onClick = {
                        toggleProfile(
                            scope,
                            isProfileOpen,
                            screenRollOffset,
                            rollTargetPx
                        ) { isProfileOpen = it }
                    }
                )
            }
        }
    }
}

@Composable
private fun StatusBarColor(){
    val view = LocalView.current
    val darkTheme = isSystemInDarkTheme()
    LaunchedEffect(darkTheme) {
        val window = (view.context as Activity).window
        val controller = WindowInsetsControllerCompat(window, view)
        controller.isAppearanceLightStatusBars = !darkTheme
    }
}

@Composable
private fun BoxContent(
    screenRollOffset: Animatable<Float, AnimationVector1D>,
    content: @Composable () -> Unit
){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .offset { IntOffset(0, screenRollOffset.value.roundToInt()) }
//            .graphicsLayer { compositingStrategy = CompositingStrategy.Offscreen }
//            .drawWithContent {
//                val rollPx = screenRollOffset.value
//                if (rollPx <= 1f) {
//                    drawContent()
//                    return@drawWithContent
//                }
//                val width = size.width
//                val cylinderDiameter = (sqrt(rollPx) * 3.6f).coerceAtLeast(1f)
//
//                drawContent()
//                drawRect(
//                    brush = Brush.verticalGradient(
//                        listOf(Color.Black.copy(0.6f), Color.Transparent),
//                        startY = 0f,
//                        endY = cylinderDiameter * 1.5f
//                    ),
//                    size = Size(width, cylinderDiameter * 1.5f)
//                )
////                drawRect(
////                    brush = Brush.verticalGradient(
////                        0.0f to Color(0xFFD1D5DB),
////                        0.3f to Color(0xFFFFFFFF),
////                        0.6f to Color(0xFFE5E7EB),
////                        1.0f to Color(0xFF9CA3AF),
////                        startY = -cylinderDiameter,
////                        endY = 0f
////                    ),
////                    topLeft = Offset(0f, -cylinderDiameter),
////                    size = Size(width, cylinderDiameter)
////                )
//            }
    ){
        content()
    }
}


private fun toggleProfile(
    scope: CoroutineScope,
    isProfileOpen: Boolean,
    screenRollOffset: Animatable<Float, AnimationVector1D>,
    rollTargetPx: Float,
    isProfileOpenFn: (Boolean) -> Unit
) {
    scope.launch {
        if (isProfileOpen) {
            screenRollOffset.animateTo(
                0f,
                spring(dampingRatio = 0.85f, stiffness = 300f)
            )
            isProfileOpenFn(false)
        } else {
            isProfileOpenFn(true)
            screenRollOffset.animateTo(
                rollTargetPx,
                spring(dampingRatio = 0.75f, stiffness = 160f)
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun Previa(){
    WalletAnimationTheme(darkTheme = true) {
        WalletAnimation()
    }
}
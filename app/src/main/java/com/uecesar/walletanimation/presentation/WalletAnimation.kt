package com.uecesar.walletanimation.presentation

import android.app.Activity
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.spring
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowInsetsControllerCompat
import com.uecesar.walletanimation.presentation.blob.BlobContent
import com.uecesar.walletanimation.presentation.blob.BlobUiState
import com.uecesar.walletanimation.presentation.components.ExpandableBubble
import com.uecesar.walletanimation.presentation.components.ProfileAvatarAnimated
import com.uecesar.walletanimation.presentation.ui.theme.ColorIridescentBorder
import com.uecesar.walletanimation.presentation.ui.theme.ColorSpaceEnd
import com.uecesar.walletanimation.presentation.ui.theme.ColorSpaceStart
import com.uecesar.walletanimation.presentation.ui.theme.SpecularWhite
import com.uecesar.walletanimation.presentation.ui.theme.WalletAnimationTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WalletAnimation() {
    val density = LocalDensity.current
    val containerSize = LocalWindowInfo.current.containerSize
    val scope = rememberCoroutineScope()
    val statusBarPadding = WindowInsets.statusBars.asPaddingValues().calculateTopPadding()

    val screenWidthDp = with(density) { containerSize.width.toDp()}
    val screenHeightDp = with(density){containerSize.height.toDp()}
    val desplazamientoPx = with(density) { (containerSize.height * 0.82f) } //usa density para convertir dp a pixeles

    val animaDesplazamiento = remember { Animatable(0f) }
    var islandExpansionProgress by remember { mutableFloatStateOf(0f) }
    var isProfileOpen by remember { mutableStateOf(false) }
    val progress by remember {
        derivedStateOf { (animaDesplazamiento.value / desplazamientoPx).coerceIn(0f, 1f) }
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
            ProfileScreen( progress = progress )

            ProfileAvatarAnimated(
                progress = progress,
                screenWidth = screenWidthDp -40.dp,
                onClick = {
                    toggleProfile(
                        scope,
                        isProfileOpen,
                        animaDesplazamiento,
                        desplazamientoPx
                    ) { isProfileOpen = it }
                }
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .offset { IntOffset(0, animaDesplazamiento.value.roundToInt()) }
            ) {
                HomeScreen()
                HeaderIcon(Modifier.align(Alignment.TopEnd), Icons.Default.Add, ) {  }
                ExpandableBubble(
                    modifier = Modifier.align(Alignment.TopCenter),
                    blobUiState = if (islandExpansionProgress == 0.0f) BlobUiState.Collapsed else BlobUiState.Expanded,
                    maxWidthDp = screenWidthDp -40.dp,
                    maxHeightDp = screenHeightDp,
                    content = {
                        BlobContent(
                            progress = islandExpansionProgress,
                            onClose = {
                                scope.launch {
                                    islandExpansionProgress = 0.0f
                                }
                            }
                        )
                        islandExpansionProgress = it
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
private fun HeaderIcon(
    modifier: Modifier,
    icon: ImageVector,
    onClick: () -> Unit
){
    Box(
        modifier = modifier
            .size(52.dp)
            .background(
                Brush.linearGradient(
                    colors = listOf(ColorSpaceStart, ColorSpaceEnd),
                    start = Offset(0f, 0f),
                    end = Offset(0f, 100f)
                ),
                CircleShape
            )
            .border(1.dp, ColorIridescentBorder, CircleShape)
            .clip(CircleShape)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = SpecularWhite,
            modifier = Modifier.size(24.dp)
        )
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
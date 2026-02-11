package com.uecesar.walletanimation.presentation

import android.graphics.drawable.Icon
import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.rememberSplineBasedDecay
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.AnchoredDraggableState
import androidx.compose.foundation.gestures.DraggableAnchors
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import com.uecesar.walletanimation.presentation.card.WetPaintCard
import com.uecesar.walletanimation.presentation.components.ExpandableBubble
import com.uecesar.walletanimation.presentation.transaction.TransactionList
import com.uecesar.walletanimation.presentation.ui.theme.ColorIridescentBorder
import com.uecesar.walletanimation.presentation.ui.theme.ColorSpaceEnd
import com.uecesar.walletanimation.presentation.ui.theme.ColorSpaceStart
import com.uecesar.walletanimation.presentation.ui.theme.SpecularWhite
import com.uecesar.walletanimation.presentation.ui.theme.WalletAnimationTheme

/**
 * Wallet content shown on the rolling foreground surface, including the expandable "Dynamic Island".
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    onProfileClick: () -> Unit,
    progress: Float,
    onIslandProgress: (Float) -> Unit
) {
    val density = LocalDensity.current
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val scope = rememberCoroutineScope()

    val startHeight = 56.dp
    val startWidth = 220.dp
    val startRadius = 28.dp
    val endMarginHorizontal = 16.dp
    val endMarginBottom = 96.dp
    val endHeight = screenHeight - endMarginBottom
    val endWidth = screenWidth - (endMarginHorizontal * 2)
    val endRadius = 42.dp

    val startHeightPx = with(density) { startHeight.toPx() }
    val endHeightPx = with(density) { endHeight.toPx() }

    val anchors = DraggableAnchors {
        BlobUiState.Collapsed at startHeightPx
        BlobUiState.Expanded at endHeightPx
    }

    val decayAnimationSpec = rememberSplineBasedDecay<Float>()
    val draggableState = remember {
        AnchoredDraggableState(
            initialValue = BlobUiState.Collapsed,
            anchors = anchors,
            positionalThreshold = { it * 0.5f },
            velocityThreshold = { with(density) { 100.dp.toPx() } },
            snapAnimationSpec = spring(dampingRatio = 0.85f, stiffness = 250f),
            decayAnimationSpec = decayAnimationSpec
        )
    }

    val currentHeightPx =
        if (draggableState.offset.isNaN()) startHeightPx else draggableState.offset
    val rawProgress =
        ((currentHeightPx - startHeightPx) / (endHeightPx - startHeightPx)).coerceIn(0f, 1f)
    val smoothProgress by animateFloatAsState(targetValue = rawProgress, label = "Smooth")

    LaunchedEffect(smoothProgress) {
        onIslandProgress(smoothProgress)
    }

    val widthCurve = CubicBezierEasing(0.4f, 0.0f, 0.2f, 1.0f)
    val widthProgress = widthCurve.transform(smoothProgress)
    val currentWidth = lerp(startWidth, endWidth, widthProgress)
    val currentRadius = lerp(startRadius, endRadius, widthProgress)

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(startHeight + 24.dp))
            WetPaintCard()
            Spacer(Modifier.height(32.dp))
            BalanceSection()
            Spacer(Modifier.height(48.dp))
            TransactionList(modifier = Modifier.weight(1f))
        }

        if (progress == 0f) HeaderIcon(Modifier.align(Alignment.TopStart), Icons.Default.Person) { onProfileClick() }
        HeaderIcon(Modifier.align(Alignment.TopEnd), Icons.Default.Add, ) {  }

//        ExpandableBubble {
//            onIslandProgress(it)
//        }
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
            .size(54.dp)
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


@Preview(showSystemUi = true)
@Composable
private fun Previa(){
    HomeScreen(
        {},
        50f,
    ) { }
}

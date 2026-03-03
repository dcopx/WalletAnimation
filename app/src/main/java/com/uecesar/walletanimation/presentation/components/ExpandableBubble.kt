package com.uecesar.walletanimation.presentation.components

import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.AnchoredDraggableState
import androidx.compose.foundation.gestures.DraggableAnchors
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.anchoredDraggable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import com.uecesar.walletanimation.presentation.blob.BlobUiState
import com.uecesar.walletanimation.presentation.ui.theme.ColorIridescentBorder
import com.uecesar.walletanimation.presentation.ui.theme.ColorSpaceEnd
import com.uecesar.walletanimation.presentation.ui.theme.ColorSpaceStart

@Composable
fun ExpandableBubble(
    modifier: Modifier = Modifier,
    blobUiState: BlobUiState = BlobUiState.Collapsed,
    startHeight: Dp = 52.dp,
    startWidth: Dp = 200.dp,
    maxHeightDp: Dp,
    maxWidthDp: Dp,
    content: @Composable (Float) -> Unit
){
    val density = LocalDensity.current
    val startRadius = 28.dp

    val startHeightPx = with(density) { startHeight.toPx() }
    val endHeightPx = with(density) { maxHeightDp.toPx() }

    val anchors = DraggableAnchors {
        BlobUiState.Collapsed at startHeightPx
        BlobUiState.Expanded at endHeightPx
    }

    val draggableState = remember {
        AnchoredDraggableState(
            initialValue = blobUiState,
            anchors = anchors
        )
    }

    val currentHeightPx =
        if (draggableState.offset.isNaN()) startHeightPx else draggableState.offset
    val rawProgress =
        ((currentHeightPx - startHeightPx) / (endHeightPx - startHeightPx)).coerceIn(0f, 1f)
    val smoothProgress by animateFloatAsState(targetValue = rawProgress, label = "Smooth")

    val widthCurve = CubicBezierEasing(0.4f, 0.0f, 0.2f, 1.0f)
    val widthProgress = widthCurve.transform(smoothProgress)
    val currentWidth = lerp(startWidth, maxWidthDp, widthProgress)

    Box(
        modifier = modifier
            .width(currentWidth)
            .height(with(density) { currentHeightPx.toDp() })
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(ColorSpaceStart, ColorSpaceEnd),
                    start = Offset(0f, 0f),
                    end = Offset(0f, Float.POSITIVE_INFINITY)
                ),
                shape = RoundedCornerShape(startRadius)
            )
            .border(
                width = 1.dp,
                brush = ColorIridescentBorder,
                shape = RoundedCornerShape(startRadius)
            )
            .anchoredDraggable(state = draggableState, orientation = Orientation.Vertical)
    ) {
        content(smoothProgress)
    }
}

@Preview(showSystemUi = true)
@Composable
private fun Previa(){
    ExpandableBubble(
        maxHeightDp = 360.dp,
        maxWidthDp = 640.dp,
        content = {}
    )
}
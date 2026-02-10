package com.uecesar.walletanimation.presentation.card

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import kotlinx.coroutines.launch
import kotlin.math.abs
import kotlin.math.roundToInt


/**
 * Interactive card with a 3D flip gesture and shimmering paint-like front.
 */
@Composable
fun WetPaintCard(modifier: Modifier = Modifier) {
    val rotation = remember { Animatable(0f) }
    val scope = rememberCoroutineScope()
    val density = LocalDensity.current.density

    val normalizedAngle = (rotation.value % 360 + 360) % 360
    val isBackVisible = normalizedAngle in 90f..270f

    val infiniteTransition = rememberInfiniteTransition(label = "Gloss")
    val shimmerPhase by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(3500, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "Shimmer"
    )

    val dragStartFace = remember { mutableStateOf(0f) }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(240.dp)
            .graphicsLayer {
                rotationY = rotation.value
                cameraDistance = 14f * density
            }
            .draggable(
                state = rememberDraggableState { delta ->
                    scope.launch {
                        val direction = if (isBackVisible) -1f else 1f
                        val current = rotation.value
                        val angleInHalfTurn = ((current - dragStartFace.value) % 180f + 180f) % 180f
                        val distanceFromMid = abs(angleInHalfTurn - 90f)
                        val magneticFactor = lerp(
                            start = 0.25f,
                            stop = 1f,
                            fraction = (distanceFromMid / 90f).coerceIn(0f, 1f)
                        )
                        val proposed = current + delta * 0.6f * magneticFactor * direction
                        val clamped = proposed.coerceIn(
                            minimumValue = dragStartFace.value - 180f,
                            maximumValue = dragStartFace.value + 180f
                        )
                        rotation.snapTo(clamped)
                    }
                },
                orientation = Orientation.Horizontal,
                onDragStarted = {
                    dragStartFace.value = (rotation.value / 180f).roundToInt() * 180f
                },
                onDragStopped = { velocity ->
                    val current = rotation.value
                    val base = dragStartFace.value
                    val offset = current - base
                    val target = when {
                        velocity > 800f -> base + 180f
                        velocity < -800f -> base - 180f
                        offset > 60f -> base + 180f
                        offset < -60f -> base - 180f
                        else -> base
                    }
                    scope.launch {
                        rotation.animateTo(
                            targetValue = target,
                            animationSpec = spring(dampingRatio = 0.65f, stiffness = 420f)
                        )
                    }
                }
            )
    ) {
        if (isBackVisible) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .graphicsLayer { rotationY = 180f }
            ) {
                WetPaintCardBack()
            }
        } else {
            WetPaintCardFront(shimmerPhase = shimmerPhase)
        }
    }
}

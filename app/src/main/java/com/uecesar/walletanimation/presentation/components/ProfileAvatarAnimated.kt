package com.uecesar.walletanimation.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import com.uecesar.walletanimation.presentation.ui.theme.ColorIridescentBorder
import com.uecesar.walletanimation.presentation.ui.theme.ColorSpaceEnd
import com.uecesar.walletanimation.presentation.ui.theme.ColorSpaceStart
import com.uecesar.walletanimation.presentation.ui.theme.SpecularWhite
import com.uecesar.walletanimation.presentation.ui.theme.WalletAnimationTheme

/**
 * Shared element avatar that interpolates between the wallet and profile states.
 */
@Composable
fun ProfileAvatarAnimated(
    progress: Float,
    screenWidth: Dp,
    onClick: () -> Unit
) {

    val startSize = 52.dp
    val startX = 0.dp
    val startY = 0.dp

    val endSize = 100.dp
    val endX = (screenWidth - endSize) / 2
    val endY = 80.dp

    val currentSize = lerp(startSize, endSize, progress)
    val currentX = lerp(startX, endX, progress)
    val currentY = lerp(startY, endY, progress)

    val currentBorderWidth = lerp(1.dp, 2.dp, progress)
    val currentIconSize = lerp(24.dp, 48.dp, progress)

    Box(
        modifier = Modifier
            .offset { IntOffset(currentX.roundToPx(), currentY.roundToPx()) }
            .size(currentSize)
            .background(
                Brush.linearGradient(
                    colors = listOf(ColorSpaceStart, ColorSpaceEnd),
                    start = Offset(0f, 0f),
                    end = Offset(0f, 100f)
                ),
                CircleShape
            )
            .border(currentBorderWidth, ColorIridescentBorder, CircleShape)
            .clip(CircleShape)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = Icons.Rounded.Person,
            contentDescription = "Profile",
            tint = SpecularWhite,
            modifier = Modifier.size(currentIconSize)
        )
    }
}

@Preview(showSystemUi = true)
@Composable
private fun Previa(){
    WalletAnimationTheme(dynamicColor = false, darkTheme = true) {
        ProfileAvatarAnimated(1f, 500.dp) { }
    }
}
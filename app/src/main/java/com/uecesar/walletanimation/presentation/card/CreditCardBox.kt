package com.uecesar.walletanimation.presentation.card

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.uecesar.walletanimation.presentation.ui.theme.ColorPlatinumBase
import com.uecesar.walletanimation.presentation.ui.theme.ColorPlatinumDark

@Composable
fun CreditCardBox(
    endOffset: Offset = Offset(0f, Float.POSITIVE_INFINITY),
    height: Dp = 250.dp,
    content: @Composable () -> Unit
){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(height)
            .clip(RoundedCornerShape(24.dp))
            .shadow(
                elevation = 20.dp,
                shape = RoundedCornerShape(24.dp)
            )
            .background(
                Brush.linearGradient(
                    colors = listOf(ColorPlatinumBase, ColorPlatinumDark),
                    start = Offset(0f, 0f),
                    end = endOffset
                )
            )
    ) {
        content()
    }
}
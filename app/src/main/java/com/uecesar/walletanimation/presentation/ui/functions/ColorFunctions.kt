package com.uecesar.walletanimation.presentation.ui.functions

import androidx.compose.ui.graphics.Color


/**
 * Deterministic color selection used for placeholder avatars.
 */
fun getRandomColor(index: Int): Color {
    val colors = listOf(
        Color(0xFFFFCC80),
        Color(0xFFEF9A9A),
        Color(0xFF80CBC4),
        Color(0xFF9FA8DA),
        Color(0xFFB39DDB),
        Color(0xFFFFAB91)
    )
    return colors[index % colors.size]
}
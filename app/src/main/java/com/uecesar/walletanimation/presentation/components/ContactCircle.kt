package com.uecesar.walletanimation.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.uecesar.walletanimation.presentation.ui.theme.ColorSpaceEnd
import com.uecesar.walletanimation.presentation.ui.theme.ColorTextSecondary


/**
 * Circular contact chip used in the expanded island contact row.
 */
@Composable
fun ContactCircle(name: String, color: Color) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(contentAlignment = Alignment.TopEnd) {
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape)
                    .background(Brush.verticalGradient(listOf(color, color.copy(alpha = 0.6f)))),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    Icons.Rounded.Person,
                    null,
                    tint = Color.White,
                    modifier = Modifier.size(28.dp)
                )
            }
            Box(
                modifier = Modifier
                    .padding(2.dp)
                    .size(14.dp)
                    .border(2.dp, ColorSpaceEnd, CircleShape)
                    .clip(CircleShape)
                    .background(Color(0xFF00E676))
            )
        }
        Spacer(Modifier.height(8.dp))
        Text(name, color = ColorTextSecondary, fontSize = 12.sp)
    }
}

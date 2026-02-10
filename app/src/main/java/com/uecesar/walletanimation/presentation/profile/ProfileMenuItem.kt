package com.uecesar.walletanimation.presentation.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.uecesar.walletanimation.presentation.ui.theme.ColorGlassSurface
import com.uecesar.walletanimation.presentation.ui.theme.ColorTextSecondary
import com.uecesar.walletanimation.presentation.ui.theme.WalletAnimationTheme


/**
 * Clickable menu row used in the profile menu list.
 */
@Composable
fun ProfileMenuItem(text: String, icon: ImageVector, isDestructive: Boolean = false) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(ColorGlassSurface, RoundedCornerShape(12.dp))
            .border(1.dp, Color.White.copy(0.05f), RoundedCornerShape(12.dp))
            .clickable { }
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                icon,
                null,
                tint = if (isDestructive) Color(0xFFEF4444) else Color.White,
                modifier = Modifier.size(20.dp)
            )
            Spacer(Modifier.width(16.dp))
            Text(
                text,
                color = if (isDestructive) Color(0xFFEF4444) else Color.White,
                fontWeight = FontWeight.Medium
            )
        }
        Icon(
            Icons.Default.ArrowForwardIos,
            null,
            tint = ColorTextSecondary.copy(0.5f),
            modifier = Modifier.size(14.dp)
        )
    }
}

@Preview(showSystemUi = true)
@Composable
private fun Previa(){
    WalletAnimationTheme(dynamicColor = false, darkTheme = true) {
        ProfileMenuItem("Account Settings", Icons.Default.Settings)
    }
}
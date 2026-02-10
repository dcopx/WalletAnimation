package com.uecesar.walletanimation.presentation.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.rounded.ArrowDownward
import androidx.compose.material.icons.rounded.ArrowUpward
import androidx.compose.material.icons.rounded.Savings
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import androidx.compose.ui.unit.sp
import com.uecesar.walletanimation.presentation.ui.theme.ColorElectricAccent
import com.uecesar.walletanimation.presentation.ui.theme.ColorGlassSurface
import com.uecesar.walletanimation.presentation.ui.theme.ColorGreenIn
import com.uecesar.walletanimation.presentation.ui.theme.ColorRedOut
import com.uecesar.walletanimation.presentation.ui.theme.ColorTextPrimary
import com.uecesar.walletanimation.presentation.ui.theme.ColorTextSecondary
import com.uecesar.walletanimation.presentation.ui.theme.WalletAnimationTheme

/**
 * Profile screen content rendered beneath the rolling wallet surface.
 */
@Composable
fun ProfileScreen(
    progress: Float,
    topPadding: Dp
) {
    val alpha = progress
//    val scale = lerp(0.92f, 1f, progress)
    val slideY = lerp(50.dp, 0.dp, progress)
    val scrollState = rememberScrollState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .graphicsLayer {
                this.alpha = alpha
//                //this.scaleX = scale
//                this.scaleY = scale
            }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .graphicsLayer { translationY = slideY.toPx() }
                .verticalScroll(scrollState)
                .padding(top = topPadding)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
//            Spacer(Modifier.height(80.dp + 100.dp))
//            Spacer(Modifier.height(24.dp))
            NameHeader()
            Spacer(Modifier.height(32.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                TransactionsBox(
                    label = "Income",
                    value = "$8.2k",
                    icon = Icons.Rounded.ArrowUpward,
                    color = ColorGreenIn
                )
                TransactionsBox(
                    label = "Spent",
                    value = "$3.4k",
                    icon = Icons.Rounded.ArrowDownward,
                    color = ColorRedOut
                )
                TransactionsBox(
                    label = "Saved",
                    value = "$12k",
                    icon = Icons.Rounded.Savings,
                    color = ColorElectricAccent
                )
            }

            Spacer(Modifier.height(40.dp))

            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                ProfileMenuItem("Account Settings", Icons.Default.Settings)
                ProfileMenuItem("Notifications", Icons.Default.Notifications)
                ProfileMenuItem("Privacy & Security", Icons.Default.Security)
            }

            Spacer(Modifier.height(24.dp))
        }
    }
}

@Composable
private fun NameHeader(){
    Text(
        "Kyriakos Georgiopoulos",
        color = Color.White,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold
    )
    Spacer(Modifier.height(4.dp))
    Text(
        "Verified Account",
        color = ColorElectricAccent,
        fontSize = 14.sp,
        fontWeight = FontWeight.Medium
    )
}

@Composable
private fun TransactionsBox(
    label: String,
    value: String,
    icon: ImageVector,
    color: Color
) {
    Column(
        modifier = Modifier
            .width(100.dp)
            .background(ColorGlassSurface, RoundedCornerShape(16.dp))
            .border(
                1.dp,
                Color.White.copy(0.1f),
                RoundedCornerShape(16.dp)
            )
            .padding(vertical = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(icon, null, tint = color, modifier = Modifier.size(20.dp))
        Spacer(Modifier.height(8.dp))
        Text(value, color = ColorTextPrimary, fontWeight = FontWeight.Bold, fontSize = 16.sp)
        Text(label, color = ColorTextSecondary, fontSize = 12.sp)
    }
}

@Preview(showSystemUi = true)
@Composable
private fun Previa(){
    WalletAnimationTheme(dynamicColor = false, darkTheme = true){
        ProfileScreen(1f, 10.dp)
    }
}
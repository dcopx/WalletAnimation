package com.uecesar.walletanimation.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.automirrored.filled.ArrowBackIos
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.rounded.ArrowDownward
import androidx.compose.material.icons.rounded.ArrowUpward
import androidx.compose.material.icons.rounded.Savings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import com.uecesar.walletanimation.presentation.ui.theme.ColorGreenIn
import com.uecesar.walletanimation.presentation.ui.theme.ColorRedOut
import com.uecesar.walletanimation.presentation.ui.theme.WalletAnimationTheme

/**
 * Profile screen content rendered beneath the rolling wallet surface.
 */
@Composable
fun ProfileScreen(
    progress: Float
) {
    val alpha = progress
    val slideY = lerp(50.dp, 0.dp, progress)
    val scrollState = rememberScrollState()

    Box(
        modifier = Modifier
            .graphicsLayer { this.alpha = alpha }
//            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .graphicsLayer { translationY = slideY.toPx() }
                .verticalScroll(scrollState)
                .padding(vertical = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            NameHeader( )
            Spacer(Modifier.height(32.dp))
            TransactionsRow()
            Spacer(Modifier.height(40.dp))
            ProfileMenu()
            Spacer(Modifier.height(24.dp))
        }
    }
}

@Composable
private fun NameHeader(){
    Column(
        modifier = Modifier
            .padding(horizontal = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "Kyriakos Georgiopoulos",
            color = MaterialTheme.colorScheme.onBackground,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(Modifier.height(4.dp))
        Text(
            "Verified Account",
            color = MaterialTheme.colorScheme.onTertiaryContainer,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
private fun TransactionsRow(){
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        TransactionItem(
            label = "Income",
            value = "$8.2k",
            icon = Icons.Rounded.ArrowUpward,
            color = ColorGreenIn
        )
        TransactionItem(
            label = "Spent",
            value = "$3.4k",
            icon = Icons.Rounded.ArrowDownward,
            color = ColorRedOut
        )
        TransactionItem(
            label = "Saved",
            value = "$12k",
            icon = Icons.Rounded.Savings,
            color = ColorElectricAccent
        )
    }
}

@Composable
private fun TransactionItem(
    label: String,
    value: String,
    icon: ImageVector,
    color: Color
) {
    Column(
        modifier = Modifier
            .width(100.dp)
            .background(MaterialTheme.colorScheme.surfaceContainer, RoundedCornerShape(16.dp))
            .border(
                1.dp,
                MaterialTheme.colorScheme.outline.copy(0.1f),
                RoundedCornerShape(16.dp)
            )
            .padding(vertical = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(icon, null, tint = color, modifier = Modifier.size(20.dp))
        Spacer(Modifier.height(8.dp))
        Text(value, color = MaterialTheme.colorScheme.onSurface, fontWeight = FontWeight.Bold, fontSize = 16.sp)
        Text(label, color = MaterialTheme.colorScheme.onSurfaceVariant, fontSize = 12.sp)
    }
}

@Composable
private fun ProfileMenu(){
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        ProfileMenuItem("Account Settings", Icons.Default.Settings){}
        ProfileMenuItem("Notifications", Icons.Default.Notifications){}
        ProfileMenuItem("Privacy & Security", Icons.Default.Security){}
    }
}

@Composable
fun ProfileMenuItem(
    text: String,
    icon: ImageVector,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(MaterialTheme.colorScheme.surfaceContainer, RoundedCornerShape(12.dp))
            .border(1.dp, MaterialTheme.colorScheme.outline.copy(0.05f), RoundedCornerShape(12.dp))
            .clickable { onClick() }
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                icon,
                null,
                tint = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.size(20.dp)
            )
            Spacer(Modifier.width(16.dp))
            Text(
                text,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Medium
            )
        }
        Icon(
            Icons.AutoMirrored.Filled.ArrowForwardIos,
            null,
            tint = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.size(14.dp)
        )
    }
}

@Preview(showSystemUi = true)
@Composable
private fun Previa(){
    WalletAnimationTheme(dynamicColor = false, darkTheme = false){
        ProfileScreen(1f)
    }
}
package com.uecesar.walletanimation.presentation.transaction

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.uecesar.walletanimation.presentation.ui.theme.ColorSpaceStart
import com.uecesar.walletanimation.presentation.ui.theme.ColorTextSecondary

/**
 * Single transaction row.
 */
@Composable
fun TransactionItem(
    icon: ImageVector,
    title: String,
    subtitle: String,
    amount: String,
    isPositive: Boolean = false,
    iconColor: Color = ColorSpaceStart
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .shadow(
                    elevation = 2.dp,
                    shape = RoundedCornerShape(14.dp),
                    spotColor = Color.Black.copy(0.1f)
                )
                .background(Color.White, RoundedCornerShape(14.dp)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = iconColor,
                modifier = Modifier.size(24.dp)
            )
        }

        Spacer(Modifier.width(16.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(title, color = ColorSpaceStart, fontSize = 16.sp, fontWeight = FontWeight.Bold)
            Text(subtitle, color = ColorTextSecondary, fontSize = 12.sp, fontWeight = FontWeight.Medium)
        }

        Text(
            amount,
            color = if (isPositive) Color(0xFF166534) else ColorSpaceStart,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
    }
}
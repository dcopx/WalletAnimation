package com.uecesar.walletanimation.presentation.card

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.uecesar.walletanimation.presentation.ui.theme.ColorElectricAccent
import com.uecesar.walletanimation.presentation.ui.theme.ColorSpaceEnd
import com.uecesar.walletanimation.presentation.ui.theme.ColorSpaceStart

/**
 * Back face of the card.
 */
@Preview(showSystemUi = true)
@Composable
fun CreditCardBack() {
    CreditCardBox(endOffset = Offset(1000f, 1000f)) {
        Column(Modifier.fillMaxWidth()) {
            Spacer(Modifier.height(30.dp))
            Magnetic()
            Spacer(Modifier.height(24.dp))
            Signature()
            Spacer(Modifier.height(24.dp))
            Name()
            Spacer(Modifier.height(10.dp))
        }
    }
}

@Composable
private fun Magnetic(){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(
                Brush.verticalGradient(
                    listOf(
                        ColorSpaceEnd,
                        ColorElectricAccent
                    )
                )
            )
    )
}

@Composable
private fun Signature(){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .height(40.dp)
                .background(Color.White.copy(alpha = 0.8f), RoundedCornerShape(4.dp))
                .border(1.dp, Color.White, RoundedCornerShape(4.dp)),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(
                "Authorized Signature",
                color = Color.Black.copy(0.7f),
                fontSize = 10.sp,
                fontStyle = FontStyle.Italic,
                modifier = Modifier.padding(start = 12.dp)
            )
        }
        Spacer(Modifier.width(12.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                "CVC",
                color = ColorSpaceStart.copy(0.6f),
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(end = 6.dp)
            )
            Box(
                modifier = Modifier
                    .width(64.dp)
                    .height(36.dp)
                    .background(Color.White, RoundedCornerShape(6.dp))
                    .shadow(2.dp, RoundedCornerShape(6.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "892",
                    color = ColorElectricAccent,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.ExtraBold,
                    fontFamily = FontFamily.Monospace,
                    letterSpacing = 2.sp
                )
            }
        }
    }
}

@Composable
private fun Name(){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 24.dp)
    ) {
        Text(
            "CARD HOLDER",
            color = ColorSpaceStart.copy(0.5f),
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 1.sp
        )
        Spacer(Modifier.height(4.dp))
        Text(
            "KYRIAKOS GEORGIOPOULOS",
            color = ColorSpaceStart,
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Serif,
            letterSpacing = 0.5.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}
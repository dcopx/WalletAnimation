package com.uecesar.walletanimation.presentation.card

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.uecesar.walletanimation.presentation.ui.theme.ColorElectricAccent
import com.uecesar.walletanimation.presentation.ui.theme.ColorPlatinumBase
import com.uecesar.walletanimation.presentation.ui.theme.ColorPlatinumDark
import com.uecesar.walletanimation.presentation.ui.theme.ColorSilverText
import com.uecesar.walletanimation.presentation.ui.theme.ColorSpaceEnd
import com.uecesar.walletanimation.presentation.ui.theme.ColorSpaceStart

/**
 * Front face of the card with paint path and shimmer.
 */
@Composable
fun WetPaintCardFront(shimmerPhase: Float) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .shadow(
                elevation = 20.dp,
                shape = RoundedCornerShape(24.dp),
                spotColor = Color.Black.copy(0.4f),
                ambientColor = Color.Black.copy(0.2f)
            )
            .clip(RoundedCornerShape(24.dp))
            .background(
                Brush.linearGradient(
                    colors = listOf(ColorPlatinumBase, ColorPlatinumDark),
                    start = Offset(0f, 0f),
                    end = Offset(0f, Float.POSITIVE_INFINITY)
                )
            )
    ) {
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .alpha(0.08f)
        ) {
            drawRect(
                brush = Brush.radialGradient(
                    colors = listOf(Color.Black, Color.Transparent),
                    radius = size.width
                ),
                blendMode = BlendMode.Overlay
            )
        }

        Canvas(modifier = Modifier.fillMaxSize()) {
            val width = size.width
            val height = size.height
            val paintPath = Path().apply {
                moveTo(0f, 0f)
                lineTo(width, 0f)
                lineTo(width, height * 0.15f)
                cubicTo(
                    width * 0.96f,
                    height * 0.15f,
                    width * 0.94f,
                    height * 0.20f,
                    width * 0.92f,
                    height * 0.50f
                )
                cubicTo(
                    width * 0.90f,
                    height * 0.65f,
                    width * 0.84f,
                    height * 0.65f,
                    width * 0.82f,
                    height * 0.40f
                )
                cubicTo(
                    width * 0.78f,
                    height * 0.25f,
                    width * 0.72f,
                    height * 0.25f,
                    width * 0.68f,
                    height * 0.60f
                )
                cubicTo(
                    width * 0.66f,
                    height * 0.92f,
                    width * 0.56f,
                    height * 0.92f,
                    width * 0.54f,
                    height * 0.60f
                )
                cubicTo(
                    width * 0.50f,
                    height * 0.30f,
                    width * 0.45f,
                    height * 0.30f,
                    width * 0.42f,
                    height * 0.70f
                )
                cubicTo(
                    width * 0.40f,
                    height * 0.85f,
                    width * 0.30f,
                    height * 0.85f,
                    width * 0.28f,
                    height * 0.55f
                )
                cubicTo(
                    width * 0.25f,
                    height * 0.25f,
                    width * 0.20f,
                    height * 0.25f,
                    width * 0.18f,
                    height * 0.45f
                )
                cubicTo(
                    width * 0.16f,
                    height * 0.55f,
                    width * 0.10f,
                    height * 0.55f,
                    width * 0.08f,
                    height * 0.35f
                )
                cubicTo(width * 0.04f, height * 0.15f, 0f, height * 0.20f, 0f, height * 0.15f)
                close()
            }
            drawPath(
                path = paintPath,
                brush = Brush.verticalGradient(
                    colors = listOf(Color.Black.copy(0.6f), Color.Transparent),
                    startY = height * 0.1f,
                    endY = height
                ),
                style = Stroke(width = 10f)
            )
            drawPath(
                path = paintPath,
                brush = Brush.verticalGradient(
                    colors = listOf(ColorSpaceStart, ColorSpaceEnd),
                    startY = 0f,
                    endY = height
                )
            )
            drawPath(
                path = paintPath,
                brush = Brush.radialGradient(
                    colors = listOf(Color.Transparent, Color.Black.copy(0.4f)),
                    center = Offset(width * 0.6f, height * 0.9f),
                    radius = width * 0.5f
                )
            )
            clipPath(paintPath) {
                val shimmerStart = -width + (width * 3 * shimmerPhase)
                drawRect(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color.White.copy(0.15f),
                            Color.Transparent
                        ),
                        start = Offset(shimmerStart, 0f),
                        end = Offset(shimmerStart + width * 0.5f, height)
                    )
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(28.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(20.dp)
                            .background(Color.White, CircleShape)
                    )
                    Spacer(Modifier.width(8.dp))
                    Text(
                        "NEXUS",
                        color = Color.White,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.ExtraBold,
                        letterSpacing = 1.sp
                    )
                }
                Text("VIRTUAL", color = ColorSilverText, fontSize = 10.sp, fontWeight = FontWeight.Bold)
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        "••",
                        color = ColorElectricAccent,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Black,
                        letterSpacing = 2.sp
                    )
                    Text(
                        "1234",
                        color = ColorSpaceStart,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Monospace,
                        modifier = Modifier.padding(start = 6.dp, top = 2.dp)
                    )
                }
                Text(
                    "VISA",
                    color = ColorSpaceStart,
                    fontWeight = FontWeight.Black,
                    fontSize = 28.sp,
                    fontStyle = FontStyle.Italic,
                    letterSpacing = (-1).sp
                )
            }
        }
    }
}

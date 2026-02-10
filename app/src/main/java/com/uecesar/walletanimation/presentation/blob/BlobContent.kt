package com.uecesar.walletanimation.presentation.blob

import android.annotation.SuppressLint
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.rounded.ChevronRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import com.uecesar.walletanimation.presentation.components.ContactCircle
import com.uecesar.walletanimation.presentation.ui.functions.getRandomColor
import com.uecesar.walletanimation.presentation.ui.theme.ColorGlassSurface
import com.uecesar.walletanimation.presentation.ui.theme.ColorSpaceStart
import com.uecesar.walletanimation.presentation.ui.theme.ColorTextPrimary
import com.uecesar.walletanimation.presentation.ui.theme.ColorTextSecondary
import com.uecesar.walletanimation.presentation.ui.theme.WalletAnimationTheme


/**
 * Content shown inside the expandable "Dynamic Island".
 */
@SuppressLint("RestrictedApi")
@Composable
fun BlobContent(progress: Float, onClose: () -> Unit) {
    Box(Modifier.fillMaxSize()) {
        if (progress < 0.5f) {
            val pillAlpha = (1f - (progress * 5f)).coerceIn(0f, 1f)
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .graphicsLayer { alpha = pillAlpha }
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .align(Alignment.Center),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("Send", color = ColorTextSecondary, fontSize = 16.sp, fontWeight = FontWeight.Medium)
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("to", color = ColorTextPrimary, fontSize = 16.sp, fontWeight = FontWeight.Medium)
                    }
                    Row(
                        horizontalArrangement = Arrangement.spacedBy((-8).dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        for (i in 0..2) {
                            Box(
                                modifier = Modifier
                                    .size(26.dp)
                                    .border(2.dp, ColorSpaceStart, CircleShape)
                                    .clip(CircleShape)
                                    .background(getRandomColor(i)),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    listOf("A", "S", "J")[i],
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Black.copy(0.6f)
                                )
                            }
                        }
                    }
                }
            }
        }

        val contentAlpha = ((progress - 0.2f) / 0.4f).coerceIn(0f, 1f)
        val contentParallax = (1f - progress) * 100f

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
                .graphicsLayer {
                    alpha = contentAlpha
                    translationY = contentParallax
                }
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Column {
                    Text("Send", color = ColorTextSecondary, fontSize = 20.sp)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        "Money To",
                        color = ColorTextPrimary,
                        fontSize = 34.sp,
                        fontWeight = FontWeight.ExtraBold,
                        letterSpacing = (-1).sp
                    )
                }
                IconButton(
                    onClick = onClose,
                    modifier = Modifier
                        .background(ColorGlassSurface, CircleShape)
                        .border(1.dp, Color.White.copy(0.1f), CircleShape)
                ) {
                    Icon(Icons.Default.Close, null, tint = ColorTextPrimary)
                }
            }

            Spacer(Modifier.height(32.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                val bubbleScale = (progress * 1.2f).coerceIn(0f, 1f)
                Row(
                    modifier = Modifier.graphicsLayer {
                        scaleX = bubbleScale
                        scaleY = bubbleScale
                    },
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    ContactCircle("Farid", Color(0xFFFF8A65))
                    ContactCircle("Shadi", Color(0xFF29B6F6))
                    ContactCircle("Cyrus", Color(0xFFB9F6CA))
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.graphicsLayer {
                        scaleX = bubbleScale
                        scaleY = bubbleScale
                    }
                ) {
                    Box(
                        modifier = Modifier
                            .size(60.dp)
                            .border(1.dp, Color.White.copy(0.2f), CircleShape)
                            .clip(CircleShape)
                            .background(Color.White.copy(0.05f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(Icons.Default.Add, null, tint = ColorTextPrimary)
                    }
                    Spacer(Modifier.height(8.dp))
                    Text("Add", color = ColorTextSecondary, fontSize = 12.sp)
                }
            }

            Spacer(Modifier.height(32.dp))
            Text("Your Contacts", color = ColorTextPrimary, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Spacer(Modifier.height(16.dp))

            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(6) { i ->
                    val start = 0.4f + (i * 0.05f)
                    val end = 1f
                    val itemProgress = ((progress - start) / (end - start)).coerceIn(0f, 1f)
                    val itemScale = lerp(0.8f, 1f, itemProgress)
                    val itemAlpha = itemProgress
                    val itemTranslationY = (1f - itemProgress) * 100f

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .graphicsLayer {
                                alpha = itemAlpha
                                scaleX = itemScale
                                scaleY = itemScale
                                translationY = itemTranslationY
                            }
                            .clip(RoundedCornerShape(22.dp))
                            .background(ColorGlassSurface)
                            .padding(12.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(48.dp)
                                .clip(CircleShape)
                                .background(getRandomColor(i)),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                listOf("A", "C", "J", "W", "D", "M")[i],
                                color = Color.Black.copy(0.6f),
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Spacer(Modifier.width(16.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                listOf(
                                    "Annette Black",
                                    "Cameron Williamson",
                                    "Jane Cooper",
                                    "Wade Warren",
                                    "Devon Lane",
                                    "Molly Sanders"
                                )[i],
                                color = ColorTextPrimary,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 16.sp
                            )
                            Text("Recent transfer", color = ColorTextSecondary, fontSize = 12.sp)
                        }
                        Icon(Icons.Rounded.ChevronRight, null, tint = ColorTextSecondary.copy(0.5f))
                    }
                }
            }

            val buttonScale = (progress * 1.5f - 0.5f).coerceIn(0f, 1f)
            Button(
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp)
                    .graphicsLayer {
                        scaleX = buttonScale
                        scaleY = buttonScale
                        alpha = buttonScale
                    },
                shape = RoundedCornerShape(32.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color.Black
                )
            ) {
                Text("Continue", fontWeight = FontWeight.Bold, fontSize = 17.sp)
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun Previa(){
    WalletAnimationTheme(dynamicColor = false, darkTheme = true) {
        BlobContent(10f){}
    }
}
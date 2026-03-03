package com.uecesar.walletanimation.presentation

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material.icons.rounded.AttachMoney
import androidx.compose.material.icons.rounded.Bolt
import androidx.compose.material.icons.rounded.DirectionsCar
import androidx.compose.material.icons.rounded.FitnessCenter
import androidx.compose.material.icons.rounded.Movie
import androidx.compose.material.icons.rounded.MusicNote
import androidx.compose.material.icons.rounded.PhoneIphone
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.uecesar.walletanimation.presentation.components.CreditCardAnimationRotation

/**
 * Wallet content shown on the rolling foreground surface, including the expandable "Dynamic Island".
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen() {
    val startHeight = 56.dp

    Box( modifier = Modifier.fillMaxSize() ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(startHeight + 10.dp))
            CreditCardAnimationRotation()
            Spacer(Modifier.height(32.dp))
            BalanceSection()
            Spacer(Modifier.height(48.dp))
            TransactionList(modifier = Modifier.weight(1f))
        }
    }
}


@Composable
private fun BalanceSection() {
    val infiniteTransition = rememberInfiniteTransition(label = "BadgeShimmer")
    val shimmerTranslate by infiniteTransition.animateFloat(
        initialValue = -100f,
        targetValue = 400f,
        animationSpec = infiniteRepeatable(
            animation = tween(3500, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "Shimmer"
    )

    val badgeBrush = Brush.linearGradient(
        colors = listOf(Color(0xFFDCFCE7), Color(0xFFF0FDF4), Color(0xFFDCFCE7)),
        start = Offset(shimmerTranslate, 0f),
        end = Offset(shimmerTranslate + 50f, 100f),
        tileMode = TileMode.Clamp
    )

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "Total Balance",
            color = MaterialTheme.colorScheme.secondary,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium
        )
        Spacer(Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.width(65.dp))
            Text(
                text = "$24,500.00",
                color = MaterialTheme.colorScheme.primary,
                fontSize = 34.sp,
                fontWeight = FontWeight.ExtraBold,
                letterSpacing = (-1).sp
            )
            Spacer(Modifier.width(8.dp))
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(6.dp))
                    .background(badgeBrush)
                    .border(1.dp, Color(0xFF166534).copy(alpha = 0.1f), RoundedCornerShape(6.dp))
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        Icons.Default.ArrowUpward,
                        contentDescription = null,
                        tint = Color(0xFF166534),
                        modifier = Modifier.size(12.dp)
                    )
                    Spacer(Modifier.width(2.dp))
                    Text(
                        "2.4%",
                        color = Color(0xFF166534),
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Composable
private fun TransactionList(modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(bottom = 24.dp)
    ) {
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "Recent Transactions",
                    color = MaterialTheme.colorScheme.secondary,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    "See All",
                    color = MaterialTheme.colorScheme.tertiary,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }

        item {
            TransactionItem(
                icon = Icons.Rounded.ShoppingCart,
                title = "Whole Foods Market",
                subtitle = "Groceries • Today",
                amount = "$124.50",
                isPositive = true
            )
        }
        item {
            TransactionItem(
                icon = Icons.Rounded.Movie,
                title = "Netflix Subscription",
                subtitle = "Entertainment • Yesterday",
                amount = "-$15.99"
            )
        }
        item {
            TransactionItem(
                icon = Icons.Rounded.Bolt,
                title = "Electric Bill",
                subtitle = "Utilities • Feb 12",
                amount = "-$85.00"
            )
        }
        item {
            TransactionItem(
                icon = Icons.Rounded.AttachMoney,
                title = "Salary Deposit",
                subtitle = "Income • Feb 01",
                amount = "+$4,250.00",
                isPositive = true
            )
        }
        item {
            TransactionItem(
                icon = Icons.Rounded.DirectionsCar,
                title = "Uber Ride",
                subtitle = "Transport • Jan 30",
                amount = "-$24.20"
            )
        }
        item {
            TransactionItem(
                icon = Icons.Rounded.PhoneIphone,
                title = "Apple Store",
                subtitle = "Electronics • Jan 28",
                amount = "-$999.00"
            )
        }
        item {
            TransactionItem(
                icon = Icons.Rounded.FitnessCenter,
                title = "Equinox Gym",
                subtitle = "Health • Jan 25",
                amount = "-$180.00"
            )
        }
        item {
            TransactionItem(
                icon = Icons.Rounded.MusicNote,
                title = "Spotify Premium",
                subtitle = "Subscription • Jan 24",
                amount = "-$12.99"
            )
        }
    }
}

@Composable
private fun TransactionItem(
    icon: ImageVector,
    title: String,
    subtitle: String,
    amount: String,
    isPositive: Boolean = false
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
                .background(
                    MaterialTheme.colorScheme.primaryContainer,
                    RoundedCornerShape(14.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onPrimaryContainer,
                modifier = Modifier.size(24.dp)
            )
        }

        Spacer(Modifier.width(16.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(title, color = MaterialTheme.colorScheme.primary, fontSize = 16.sp, fontWeight = FontWeight.Bold)
            Text(subtitle, color = MaterialTheme.colorScheme.secondary, fontSize = 12.sp, fontWeight = FontWeight.Medium)
        }

        Text(
            amount,
            color = if (isPositive) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.tertiary,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview(showSystemUi = true)
@Composable
private fun Previa(){
    HomeScreen()
}

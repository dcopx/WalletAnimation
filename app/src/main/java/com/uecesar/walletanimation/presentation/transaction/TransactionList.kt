package com.uecesar.walletanimation.presentation.transaction

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AttachMoney
import androidx.compose.material.icons.rounded.Bolt
import androidx.compose.material.icons.rounded.DirectionsCar
import androidx.compose.material.icons.rounded.FitnessCenter
import androidx.compose.material.icons.rounded.Movie
import androidx.compose.material.icons.rounded.MusicNote
import androidx.compose.material.icons.rounded.PhoneIphone
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.uecesar.walletanimation.presentation.ui.theme.ColorElectricAccent
import com.uecesar.walletanimation.presentation.ui.theme.ColorSpaceStart


/**
 * Scrollable list of recent transactions.
 */
@Composable
fun TransactionList(modifier: Modifier = Modifier) {
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
                    color = ColorSpaceStart,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    "See All",
                    color = ColorElectricAccent,
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
                amount = "-$124.50",
                iconColor = ColorSpaceStart
            )
        }
        item {
            TransactionItem(
                icon = Icons.Rounded.Movie,
                title = "Netflix Subscription",
                subtitle = "Entertainment • Yesterday",
                amount = "-$15.99",
                iconColor = ColorSpaceStart
            )
        }
        item {
            TransactionItem(
                icon = Icons.Rounded.Bolt,
                title = "Electric Bill",
                subtitle = "Utilities • Feb 12",
                amount = "-$85.00",
                iconColor = ColorSpaceStart
            )
        }
        item {
            TransactionItem(
                icon = Icons.Rounded.AttachMoney,
                title = "Salary Deposit",
                subtitle = "Income • Feb 01",
                amount = "+$4,250.00",
                isPositive = true,
                iconColor = ColorSpaceStart
            )
        }
        item {
            TransactionItem(
                icon = Icons.Rounded.DirectionsCar,
                title = "Uber Ride",
                subtitle = "Transport • Jan 30",
                amount = "-$24.20",
                iconColor = ColorSpaceStart
            )
        }
        item {
            TransactionItem(
                icon = Icons.Rounded.PhoneIphone,
                title = "Apple Store",
                subtitle = "Electronics • Jan 28",
                amount = "-$999.00",
                iconColor = ColorSpaceStart
            )
        }
        item {
            TransactionItem(
                icon = Icons.Rounded.FitnessCenter,
                title = "Equinox Gym",
                subtitle = "Health • Jan 25",
                amount = "-$180.00",
                iconColor = ColorSpaceStart
            )
        }
        item {
            TransactionItem(
                icon = Icons.Rounded.MusicNote,
                title = "Spotify Premium",
                subtitle = "Subscription • Jan 24",
                amount = "-$12.99",
                iconColor = ColorSpaceStart
            )
        }
    }
}

package com.uecesar.walletanimation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.uecesar.walletanimation.presentation.WalletAnimation
import com.uecesar.walletanimation.presentation.ui.theme.WalletAnimationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WalletAnimationTheme(darkTheme = true, dynamicColor = false) {
                WalletAnimation()
            }
        }
    }
}

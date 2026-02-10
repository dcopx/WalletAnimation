package com.uecesar.walletanimation.presentation

sealed class BlobUiState {
    object Collapsed: BlobUiState()
    object Expanded: BlobUiState()
}
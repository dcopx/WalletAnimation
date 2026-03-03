package com.uecesar.walletanimation.presentation.blob

sealed class BlobUiState {
    object Collapsed: BlobUiState()
    object Expanded: BlobUiState()
}
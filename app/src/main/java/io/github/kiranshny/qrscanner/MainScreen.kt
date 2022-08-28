package io.github.kiranshny.qrscanner

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable

@Composable
fun MainScreen() {
    Scaffold(
        topBar = {
            CartSummaryTopBar()
        }
    ) {

    }
}
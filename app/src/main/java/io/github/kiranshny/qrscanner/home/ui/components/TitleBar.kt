package io.github.kiranshny.qrscanner.home.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TitleBar(
    modifier: Modifier = Modifier
) {
    Text(
        text = "Scanner",
        fontSize = 20.sp,
        fontWeight = FontWeight.W700,
        modifier = modifier.padding(start = 16.dp, top = 26.dp)
    )
}
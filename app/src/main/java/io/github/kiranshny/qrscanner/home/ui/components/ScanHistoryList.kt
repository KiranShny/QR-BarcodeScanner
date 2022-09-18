package io.github.kiranshny.qrscanner.home.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.*
import io.github.kiranshny.qrscanner.R
import io.github.kiranshny.qrscanner.home.domain.HomeState
import io.github.kiranshny.qrscanner.home.theme.QRScannerTheme
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun ScanHistoryList(homeState: HomeState) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Title()
        when (homeState) {
            is HomeState.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier
                        .padding(24.dp)
                        .width(56.dp)
                        .height(56.dp)
                        .align(Alignment.CenterHorizontally)
                )
            }
            is HomeState.ScanHistoryEmpty -> {
                val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.scan))
                val animation by animateLottieCompositionAsState(
                    composition,
                    iterations = LottieConstants.IterateForever,
                )
                LottieAnimation(
                    composition = composition,
                    progress = { animation },
                    modifier = Modifier.fillMaxWidth()
                        .height(180.dp)
                        .padding(top = 16.dp, bottom = 24.dp, start = 16.dp, end = 16.dp)
                        .align(Alignment.CenterHorizontally)
                )
                Text(
                    text = "Start by scanning a barcode,\nusing the button above",
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 24.dp)
                        .align(Alignment.CenterHorizontally)
                )
            }
            is HomeState.ScanHistoryFetched -> {
                val dateFormatter = SimpleDateFormat("dd MMMM yyyy, HH:mm:ss", Locale.ENGLISH)
                LazyColumn(modifier = Modifier.fillMaxWidth()) {
                    items(homeState.scanHistory) { item ->
                        ScanHistoryItem(
                            item = item,
                            modifier = Modifier.fillMaxWidth(),
                            dateFormatter = dateFormatter
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun Title() {
    Text(
        text = "Previously Scanned Items",
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 24.dp)
    )
}

@Preview
@Composable
fun ScanHistoryListPreview() {
    QRScannerTheme {
        ScanHistoryList(homeState = HomeState.Loading)
    }
}
package io.github.kiranshny.qrscanner.home.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.*
import io.github.kiranshny.qrscanner.R
import io.github.kiranshny.qrscanner.home.theme.QRScannerTheme

@Composable
fun PermissionBottomSheet(
    title: String,
    subtitle: String,
    buttonText: String,
    onButtonClick: () -> Unit
) {
    Column {
        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.camera))
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
            text = title,
            modifier = Modifier.fillMaxWidth()
                .padding(bottom = 16.dp, start = 16.dp, end = 16.dp)
                .align(Alignment.CenterHorizontally),
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        )
        Text(
            text = subtitle,
            modifier = Modifier.fillMaxWidth()
                .padding(bottom = 24.dp, start = 16.dp, end = 16.dp)
                .align(Alignment.CenterHorizontally),
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Center
            )
        )
        Button(
            onClick = onButtonClick,
            modifier = Modifier
                .width(200.dp)
                .padding(bottom = 56.dp)
                .align(Alignment.CenterHorizontally),
            content = {
                Text(text = buttonText)
            }
        )
    }
}

@Preview
@Composable
fun PermissionBottomSheetPreview() {
    QRScannerTheme {
        PermissionBottomSheet(
            title = "Error",
            subtitle = "Error Sub",
            buttonText = "Confirm"
        ) {
        }
    }
}

package io.github.kiranshny.qrscanner.home.ui.components

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import iogithubkiranshnyqrscanner.ScanHistory
import java.text.SimpleDateFormat

@Composable
fun ScanHistoryItem(
    modifier: Modifier = Modifier,
    item: ScanHistory,
    dateFormatter: SimpleDateFormat,
    onClick: (ScanHistory) -> Unit,
    onLongClick: (ScanHistory) -> Unit
) {
    Card(
        modifier = modifier
            .padding(start = 16.dp, bottom = 16.dp, end = 16.dp)
            .pointerInput(Unit) {
                detectTapGestures(
                    onPress = { onClick(item) },
                    onLongPress = { onLongClick(item) }
                )
            }
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            Column {
                Text(
                    text = item.content,
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 16.sp
                )
                Text(
                    text = dateFormatter.format(item.timeStamp),
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp),
                    fontSize = 14.sp
                )

            }
        }
    }
}
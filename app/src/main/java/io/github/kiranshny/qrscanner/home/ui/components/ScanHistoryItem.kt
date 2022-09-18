package io.github.kiranshny.qrscanner.home.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import iogithubkiranshnyqrscanner.ScanHistory
import java.text.SimpleDateFormat

@Composable
fun ScanHistoryItem(
    item: ScanHistory,
    dateFormatter: SimpleDateFormat,
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier) {
        Row {
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
package io.github.kiranshny.qrscanner.exts

import android.content.Context
import android.content.Intent
import android.net.Uri

typealias CardDismissListener = () -> Unit
typealias LoadingCardDismissListener = () -> Unit

fun String.launch(context: Context) {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(this))
    context.startActivity(intent)
}
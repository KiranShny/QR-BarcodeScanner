package io.github.kiranshny.qrscanner.exts

import android.content.Context
import android.content.Intent
import android.net.Uri

fun String.launch(context: Context): Boolean {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(this))
    return if (intent.resolveActivity(context.packageManager) != null) {
        context.startActivity(intent)
        true
    } else {
        false
    }
}
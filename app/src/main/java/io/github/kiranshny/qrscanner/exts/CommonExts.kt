package io.github.kiranshny.qrscanner.exts

import android.content.Context
import android.content.Intent
import android.net.Uri

private const val WEBSITE_REGEX =
    "^https?:\\/\\/(?:www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b(?:[-a-zA-Z0-9()@:%_\\+.~#?&\\/=]*)$"

fun String.launch(context: Context): Boolean {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(this))
    return if (this.matches(Regex(WEBSITE_REGEX))) {
        context.startActivity(intent)
        true
    } else if (intent.resolveActivity(context.packageManager) != null) {
        context.startActivity(intent)
        true
    } else {
        false
    }
}
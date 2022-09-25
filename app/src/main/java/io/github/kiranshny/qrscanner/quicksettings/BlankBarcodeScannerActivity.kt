package io.github.kiranshny.qrscanner.quicksettings

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import io.github.kiranshny.qrscanner.exts.launch
import io.github.kiranshny.qrscanner.home.domain.BarCodeContract
import io.github.kiranshny.qrscanner.quicksettings.domain.BarcodeScannerViewModel

@AndroidEntryPoint
class BlankBarcodeScannerActivity : AppCompatActivity() {

    private val viewModel by viewModels<BarcodeScannerViewModel>()

    private val barcodeLauncher = registerForActivityResult(BarCodeContract()) { result ->
        result.qrCodeContent?.let { scannedContent ->
            scannedContent.launch(this).also { isLaunched ->
                if (!isLaunched) {
                    val clipboardManager = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                    val clipData = ClipData.newPlainText("text", scannedContent)
                    clipboardManager.setPrimaryClip(clipData)
                    Toast.makeText(this, "Copied to clipboard!", Toast.LENGTH_LONG).show()
                }
            }
            viewModel.saveContent(content = scannedContent)
        }
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        barcodeLauncher.launch(Unit)
    }
}
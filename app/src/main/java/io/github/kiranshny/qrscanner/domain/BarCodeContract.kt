package io.github.kiranshny.qrscanner.domain

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract
import io.github.kiranshny.qrscanner.model.QRCodeResult
import io.github.kiranshny.qrscanner.scanner.BarcodeScannerActivity

const val BARCODE_QR_CONTENT = "bar-code-qr-content"
const val BARCODE_QR_FILE = "bar-code-qr-file"

class BarCodeContract : ActivityResultContract<Unit, QRCodeResult>() {
    override fun createIntent(context: Context, input: Unit): Intent {
        return Intent(context, BarcodeScannerActivity::class.java)
    }

    override fun parseResult(resultCode: Int, intent: Intent?): QRCodeResult {
        return if (resultCode == Activity.RESULT_OK) {
            QRCodeResult(
                qrCodeContent = intent?.getStringExtra(BARCODE_QR_CONTENT),
                qrCodeFilePath = intent?.getStringExtra(BARCODE_QR_FILE)
            )
        } else {
            QRCodeResult()
        }
    }
}
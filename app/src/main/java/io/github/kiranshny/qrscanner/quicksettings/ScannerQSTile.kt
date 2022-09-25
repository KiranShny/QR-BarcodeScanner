package io.github.kiranshny.qrscanner.quicksettings

import android.content.Intent
import android.service.quicksettings.TileService
import dagger.hilt.android.AndroidEntryPoint
import io.github.kiranshny.qrscanner.home.domain.QSTileStateStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class ScannerQSTile : TileService() {

    @Inject
    lateinit var tileStateStore: QSTileStateStore

    override fun onTileAdded() {
        super.onTileAdded()
        CoroutineScope(Dispatchers.IO).launch {
            tileStateStore.setQSSileState(state = true)
        }
    }

    override fun onClick() {
        super.onClick()
        Intent(this, BlankBarcodeScannerActivity::class.java).apply {
            this.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivityAndCollapse(this)
        }
    }

    override fun onTileRemoved() {
        super.onTileRemoved()
        CoroutineScope(Dispatchers.IO).launch {
            tileStateStore.setQSSileState(state = false)
        }
    }
}
package io.github.kiranshny.qrscanner.home.domain

import iogithubkiranshnyqrscanner.ScanHistory
import javax.inject.Inject

class ScanHistoryRepo @Inject constructor(
    private val localDataStore: ScanHistoryLocalDataStore
) {
    fun saveScanHistory(content: String) = localDataStore.insert(
        ScanHistory(
            content = content,
            timeStamp = System.currentTimeMillis()
        )
    )

    fun fetchScanHistory() = localDataStore.fetchAll()
    fun clear() = localDataStore.clear()
}
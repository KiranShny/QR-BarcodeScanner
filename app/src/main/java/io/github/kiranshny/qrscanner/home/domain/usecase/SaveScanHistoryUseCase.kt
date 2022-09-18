package io.github.kiranshny.qrscanner.home.domain.usecase

import io.github.kiranshny.qrscanner.home.domain.ScanHistoryRepo
import javax.inject.Inject

class SaveScanHistoryUseCase @Inject constructor(private val repo: ScanHistoryRepo) {
    operator fun invoke(scanContent: String) = repo.saveScanHistory(scanContent)
}
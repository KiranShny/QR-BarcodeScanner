package io.github.kiranshny.qrscanner.home.domain.usecase

import io.github.kiranshny.qrscanner.home.domain.ScanHistoryRepo
import javax.inject.Inject

class FetchScanHistoryUseCase @Inject constructor(private val repo: ScanHistoryRepo) {
    operator fun invoke() = repo.fetchScanHistory()
}
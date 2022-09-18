package io.github.kiranshny.qrscanner.home.domain.usecase

import io.github.kiranshny.qrscanner.home.domain.ScanHistoryRepo
import javax.inject.Inject

class ClearScanHistoryUseCase @Inject constructor(private val repo: ScanHistoryRepo) {
    operator fun invoke() = repo.clear()
}
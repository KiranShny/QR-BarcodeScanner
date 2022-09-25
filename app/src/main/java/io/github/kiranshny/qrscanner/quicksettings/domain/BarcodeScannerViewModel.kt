package io.github.kiranshny.qrscanner.quicksettings.domain

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.kiranshny.qrscanner.home.domain.usecase.SaveScanHistoryUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BarcodeScannerViewModel @Inject constructor(
    private val saveScanHistory: SaveScanHistoryUseCase
) : ViewModel() {

    fun saveContent(content: String) {
        viewModelScope.launch {
            saveScanHistory(content)
        }
    }

}
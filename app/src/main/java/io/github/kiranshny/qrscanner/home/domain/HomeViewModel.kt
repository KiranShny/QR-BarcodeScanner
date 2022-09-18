package io.github.kiranshny.qrscanner.home.domain

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.kiranshny.qrscanner.home.domain.usecase.ClearScanHistoryUseCase
import io.github.kiranshny.qrscanner.home.domain.usecase.FetchScanHistoryUseCase
import io.github.kiranshny.qrscanner.home.domain.usecase.SaveScanHistoryUseCase
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val clearScanHistory: ClearScanHistoryUseCase,
    private val saveScanHistory: SaveScanHistoryUseCase,
    private val fetchScanHistory: FetchScanHistoryUseCase
) : ViewModel() {

    var homeState: HomeState by mutableStateOf(HomeState.Loading)
        private set

    fun saveContent(content: String) {
        viewModelScope.launch {
            saveScanHistory(content)
            fetchAndUpdateState()
        }
    }

    fun loadScanHistory() {
        homeState = HomeState.Loading
        viewModelScope.launch {
            fetchAndUpdateState()
        }
    }

    fun removeAllScanHistory() {
        viewModelScope.launch {
            clearScanHistory()
            fetchAndUpdateState()
        }
    }

    private suspend fun fetchAndUpdateState() = fetchScanHistory()
        .collectLatest { list ->
            homeState = if (list.isEmpty()) {
                HomeState.ScanHistoryEmpty
            } else {
                HomeState.ScanHistoryFetched(list)
            }
        }
}
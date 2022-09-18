package io.github.kiranshny.qrscanner.home.domain

import iogithubkiranshnyqrscanner.ScanHistory

sealed class HomeState {
    data class ScanHistoryFetched( val scanHistory: List<ScanHistory>): HomeState()
    object ScanHistoryEmpty: HomeState()
    object Loading: HomeState()
}
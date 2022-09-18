package io.github.kiranshny.qrscanner.home.domain

import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import iogithubkiranshnyqrscanner.ScanHistory
import iogithubkiranshnyqrscanner.ScanHistoryQueries
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ScanHistoryLocalDataStore @Inject constructor(
    private val queries: ScanHistoryQueries
) {
    fun insert(history: ScanHistory) {
        queries.insert(
            timeStamp = history.timeStamp,
            content = history.content
        )
    }

    fun clear() {
        queries.clear()
    }

    fun fetchAll(): Flow<List<ScanHistory>> {
        return queries.fetchAll().asFlow().mapToList()
    }
}
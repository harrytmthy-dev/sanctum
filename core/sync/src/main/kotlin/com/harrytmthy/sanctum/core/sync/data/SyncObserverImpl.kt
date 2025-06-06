/*
 * Copyright 2025 Harry Timothy Tumalewa
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.harrytmthy.sanctum.core.sync.data

import com.harrytmthy.sanctum.core.common.constants.SessionConstants.PREF_USER_ID
import com.harrytmthy.sanctum.core.common.sync.SyncManager
import com.harrytmthy.sanctum.core.cryptography.data.PrefsObserver
import com.harrytmthy.sanctum.core.database.dao.EntrySyncDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNot
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.sample
import kotlinx.coroutines.flow.transform
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class SyncObserverImpl @Inject constructor(
    private val prefsObserver: PrefsObserver,
    private val entrySyncDao: EntrySyncDao,
    private val syncManager: SyncManager,
) : SyncObserver {

    override fun observeSync(): Flow<Unit> =
        prefsObserver.observe<String>(PREF_USER_ID)
            .filterNot { it.isNullOrEmpty() }
            .flatMapLatest {
                entrySyncDao.observePendingEntries()
                    .sample(THROTTLE_DURATION_IN_MILLIS)
                    .transform {
                        syncManager.sync(false)
                        emit(Unit)
                    }
            }

    companion object {
        private const val THROTTLE_DURATION_IN_MILLIS = 2000L
    }
}

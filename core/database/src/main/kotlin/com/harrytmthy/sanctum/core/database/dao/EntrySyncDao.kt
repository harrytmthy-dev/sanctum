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

package com.harrytmthy.sanctum.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.harrytmthy.sanctum.core.database.model.EntrySyncEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface EntrySyncDao {

    @Query("SELECT * FROM entries_sync")
    fun observePendingEntries(): Flow<List<EntrySyncEntity>>

    @Upsert
    suspend fun upsertPendingEntry(entrySync: EntrySyncEntity)

    @Query("DELETE FROM entries_sync WHERE entryId IN (:entryIds)")
    suspend fun deletePendingEntryByIds(entryIds: List<String>)
}

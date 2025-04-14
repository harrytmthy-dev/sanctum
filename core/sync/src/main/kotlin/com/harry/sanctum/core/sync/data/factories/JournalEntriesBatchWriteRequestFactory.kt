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

package com.harry.sanctum.core.sync.data.factories

import android.content.SharedPreferences
import com.harry.sanctum.core.common.BatchWriteRequest
import com.harry.sanctum.core.common.Document
import com.harry.sanctum.core.common.Write
import com.harry.sanctum.core.common.constants.SessionConstants.ERROR_NULL_USER_ID
import com.harry.sanctum.core.common.constants.SessionConstants.PREF_USER_ID
import com.harry.sanctum.core.common.di.EncryptedPrefs
import com.harry.sanctum.core.common.factories.BatchWriteRequestFactory
import com.harry.sanctum.core.database.model.EntryEntity
import com.harry.sanctum.core.network.di.qualifiers.BaseUrl
import com.harry.sanctum.core.sync.data.EntryPayload
import javax.inject.Singleton

@Singleton
internal class JournalEntriesBatchWriteRequestFactory(
    @BaseUrl private val baseUrl: String,
    @EncryptedPrefs private val prefs: SharedPreferences,
) : BatchWriteRequestFactory<EntryEntity, EntryPayload> {

    override fun create(entities: List<EntryEntity>): BatchWriteRequest<EntryPayload> {
        val userId = prefs.getString(PREF_USER_ID, null) ?: error(ERROR_NULL_USER_ID)
        val documentWrites = entities.map {
            val payload = EntryPayload(
                it.entryId,
                it.title,
                it.content,
                it.createdAt.toEpochMilliseconds(),
                it.updatedAt.toEpochMilliseconds(),
                it.deletedAt?.toEpochMilliseconds(),
            )
            val document = Document(
                name = "${baseUrl}documents/journals/$userId/entries/${it.entryId}}",
                fields = payload,
            )
            Write(document)
        }
        return BatchWriteRequest(documentWrites)
    }
}

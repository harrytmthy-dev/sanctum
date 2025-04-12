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

package com.harry.sanctum.core.sync.data

import com.harry.sanctum.core.common.ApiResponse
import com.harry.sanctum.core.common.Document
import com.harry.sanctum.core.common.DocumentWrapper
import com.harry.sanctum.core.common.StructuredQueryRequest
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface EntryApi {

    @POST(":runQuery")
    suspend fun getJournalEntries(
        @Body request: StructuredQueryRequest,
    ): ApiResponse<List<DocumentWrapper<EntryPayload>>>

    @POST("journals/{userId}/entries")
    suspend fun upsertEntry(
        @Path("userId") userId: String,
        @Body document: Document<EntryPayload>,
    ): ApiResponse<Document<EntryPayload>>
}

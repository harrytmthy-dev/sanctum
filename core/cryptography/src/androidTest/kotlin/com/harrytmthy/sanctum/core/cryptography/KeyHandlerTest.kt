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

package com.harrytmthy.sanctum.core.cryptography

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.runner.RunWith
import kotlin.test.Test
import kotlin.test.assertTrue

@RunWith(AndroidJUnit4::class)
class KeyHandlerTest {

    @Test
    fun generateAesKey_shouldReturnNonEmptyKey() {
        assertTrue(KeyHandler.generateAesKey().isNotEmpty())
    }

    @Test
    fun generateAesKey_withConsecutiveCall_shouldReturnDifferentKeys() {
        assertTrue(KeyHandler.generateAesKey() != KeyHandler.generateAesKey())
    }

    @Test
    fun deriveSecretKeyFromPin_shouldReturnNonEmptyEncodedString() {
        val pin = "123456".toCharArray()
        val salt = Cryptography.generateSalt()

        val result = KeyHandler.deriveSecretKeyFromPin(pin, salt)

        assertTrue(result.isNotEmpty())
    }
}

/*
 * Copyright 2025 Harry Timothy Tumalewa
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * imitations under the License.
 */

val ktlintVersion = "1.5.0"

initscript {
    val spotlessVersion = "7.0.2"
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath("com.diffplug.spotless:spotless-plugin-gradle:$spotlessVersion")
    }
}

rootProject {
    subprojects {
        apply<com.diffplug.gradle.spotless.SpotlessPlugin>()
        extensions.configure<com.diffplug.gradle.spotless.SpotlessExtension> {
            kotlin {
                target("**/*.kt")
                targetExclude("**/build/**/*.kt")
                targetExclude("**/Example*Test.kt")
                ktlint(ktlintVersion).editorConfigOverride(mapOf("android" to "true"))
                licenseHeaderFile(rootProject.file("spotless/copyright.kt"))
            }
            format("kts") {
                target("**/*.kts")
                targetExclude("**/build/**/*.kts")
                licenseHeaderFile(rootProject.file("spotless/copyright.kts"), "(^(?![\\/ ]\\*).*$)")
            }
            format("xml") {
                target("**/*.xml")
                targetExclude(
                    "**/build/**/*.xml",
                    "**/ic_launcher*.xml",
                    "**/themes.xml",
                    "**/colors.xml",
                    "**/data_extraction_rules.xml",
                )
                licenseHeaderFile(rootProject.file("spotless/copyright.xml"), "(<[^!?])")
            }
        }
    }
}
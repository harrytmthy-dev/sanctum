import com.android.build.gradle.LibraryExtension
import com.harry.sanctum.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class FeatureConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            apply(plugin = "convention.library")
            apply(plugin = "convention.hilt")
            apply(plugin = "org.jetbrains.kotlin.plugin.serialization")
            extensions.configure<LibraryExtension> {
                testOptions.animationsDisabled = true
            }
            dependencies {
                "implementation"(libs.findLibrary("androidx.hilt.navigation.compose").get())
                "implementation"(libs.findLibrary("androidx.lifecycle.runtimeCompose").get())
                "implementation"(libs.findLibrary("androidx.lifecycle.viewModelCompose").get())
                "implementation"(libs.findLibrary("androidx.navigation.compose").get())
                "implementation"(libs.findLibrary("kotlinx.serialization.json").get())
                "testImplementation"(libs.findLibrary("androidx.navigation.testing").get())
                "androidTestImplementation"(
                    libs.findLibrary("androidx.lifecycle.runtimeTesting").get(),
                )
            }
        }
    }
}

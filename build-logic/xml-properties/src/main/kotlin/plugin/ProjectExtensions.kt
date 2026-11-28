/*
 * Copyright [2023-2024] [KnightWood]
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
 * limitations under the License.
 *
 */

package plugin

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType

val Project.outerLibs
    get(): VersionCatalog = extensions.getByType<VersionCatalogsExtension>()
        .named("libs")

val implementationDeps: String
    get() = "implementation"
val compileOnlyDeps: String
    get() = "compileOnly"

val annotationProcessorDeps: String
    get() = "annotationProcessor"

val kaptDeps: String get() = "kapt"
val kspDeps: String get() = "ksp"
val testImplementationDeps: String get() = "testImplementation"

val apiDeps: String get() = "api"

val debugImplementationDeps: String get() = "debugImplementation"

val androidTestImplementationDeps: String get() = "androidTestImplementation"

val Project.isAndroidLibrary get() = pluginManager.hasPlugin("com.android.library")

fun Project.configAndroidApplication(action: ApplicationExtension.() -> Unit) {
    val isLibrary: Boolean = pluginManager.hasPlugin("com.android.library")
    if (isLibrary) return
    extensions.getByType<ApplicationExtension>().apply(action)
}

fun Project.configAndroidLibrary(action: LibraryExtension.() -> Unit) {
    val isLibrary: Boolean = pluginManager.hasPlugin("com.android.library")
    if (!isLibrary) return
    extensions.getByType<LibraryExtension>().apply(action)
}

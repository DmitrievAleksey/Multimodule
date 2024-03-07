package com.example.plugins.common

import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import com.example.plugins.common.internal.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.findByType

class AndroidComposeLibraryPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {

        val extension = extensions.findByType<BaseAppModuleExtension>() ?: extensions.findByType<LibraryExtension>()
        extension?.apply{
            buildFeatures.compose = true
            composeOptions.kotlinCompilerExtensionVersion = libs.findVersion("androidxComposeCompiler").get().toString()
        }

        dependencies {
            add("implementation", platform(libs.findLibrary("androidx-compose-bom").get()))
            add("implementation", libs.findBundle("androidx-compose").get())
            add("implementation", libs.findLibrary("androidx-compose-ui-tooling-preview").get())
            add("implementation", libs.findLibrary("androidx-compose-ui-tooling").get())
        }
    }
}
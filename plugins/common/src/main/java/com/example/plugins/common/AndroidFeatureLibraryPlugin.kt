package com.example.plugins.common

import com.android.build.gradle.LibraryExtension
import com.example.plugins.common.internal.configureAndroidKotlin
import com.example.plugins.common.internal.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class AndroidFeatureLibraryPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {

        with(pluginManager) {
            apply("com.android.library")
            apply("org.jetbrains.kotlin.android")
            apply("com.example.android.compose-library")
            apply("org.jetbrains.kotlin.kapt")
        }

        extensions.configure<LibraryExtension> {
            configureAndroidKotlin()
            defaultConfig.targetSdk = 34
        }

        dependencies {
            add("implementation", libs.findLibrary("kotlin-std").get())
            add("api", libs.findBundle("kotlin-coroutines").get())
            add("testImplementation", libs.findBundle("tests-unit").get())
            add("androidTestImplementation", libs.findBundle("tests-android").get())
        }
    }
}

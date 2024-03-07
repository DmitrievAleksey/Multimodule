import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}

repositories {
    google()
    mavenCentral()
}

group = "com.example.plugins.common"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

gradlePlugin {
    plugins {
        register("android-core") {
            id = "com.example.android.core-library"
            implementationClass = "com.example.plugins.common.AndroidCoreLibraryPlugin"
        }
        register("android-feature") {
            id = "com.example.android.feature-library"
            implementationClass = "com.example.plugins.common.AndroidFeatureLibraryPlugin"
        }
        register("android-compose") {
            id = "com.example.android.compose-library"
            implementationClass = "com.example.plugins.common.AndroidComposeLibraryPlugin"
        }
    }
}

dependencies {
    implementation(libs.gradle.android)
    implementation(libs.gradle.kotlin)
}

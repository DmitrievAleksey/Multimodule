plugins {
    id("com.example.android.core-library")
}

android {
    namespace = "com.example.core.data"
}

dependencies {
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
    api(libs.bundles.kotlin.coroutines)
}

plugins {
    id("com.example.android.core-library")
    id("com.example.android.compose-library")
}

android {
    namespace = "com.example.core.design_system"
}

dependencies {
    api(libs.bundles.androidx.compose)
    implementation(libs.material)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity)
}
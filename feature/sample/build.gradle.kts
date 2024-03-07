plugins {
    id("com.example.android.feature-library")
}

android {
    namespace = "com.example.feature.sample"
}

dependencies {
    implementation(project(":core:design-system"))
    implementation(project(":core:data"))

    // Hilt Dependency Injection
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    // Arch Components
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.hilt.navigation.compose)
}
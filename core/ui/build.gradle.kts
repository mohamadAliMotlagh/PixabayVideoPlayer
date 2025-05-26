plugins {
    alias(libs.plugins.pixabay.android.library)
    alias(libs.plugins.pixabay.android.library.compose)
}

android {
    namespace = "com.motlagh.core.ui"
}

dependencies {
    api(projects.core.designsystem)
    implementation(libs.coil.kt)
    implementation(libs.coil.kt.compose)
    androidTestImplementation(projects.core.testing)
}
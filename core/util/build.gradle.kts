plugins {
    alias(libs.plugins.pixabay.android.library)
    alias(libs.plugins.pixabay.hilt)
    alias(libs.plugins.pixabay.android.library.compose)
}

android {
    namespace = "com.motlagh.core.util"
}
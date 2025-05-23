plugins {
    alias(libs.plugins.pixabay.android.library)
    alias(libs.plugins.pixabay.android.library.compose)
}

android {
    namespace = "com.motlagh.core.mvi"
}

dependencies{
    implementation(projects.core.util)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
}
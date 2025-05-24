plugins {
    alias(libs.plugins.pixabay.android.feature)
    alias(libs.plugins.pixabay.android.library.compose)
}

android {
    namespace = "com.motlagh.feature.search"
}
dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(projects.core.testing)
    implementation(projects.core.util)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation(projects.core.database)
    api(projects.core.network)
}
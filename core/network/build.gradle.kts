plugins {
    alias(libs.plugins.pixabay.android.library)
    alias(libs.plugins.pixabay.hilt)
    id("kotlinx-serialization")
}

android {
    namespace = "com.motlagh.core.network"
}

dependencies {
    api(libs.coil.kt)
    api(libs.coil.kt.svg)
    api(libs.kotlinx.serialization.json)
    implementation(libs.okhttp.logging)
    api(libs.retrofit.core)
    api(libs.retrofit.kotlin.serialization)
}
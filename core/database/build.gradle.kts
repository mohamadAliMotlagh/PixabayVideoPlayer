plugins {
    alias(libs.plugins.pixabay.android.library)
    alias(libs.plugins.pixabay.android.room)
    alias(libs.plugins.pixabay.hilt)
}

android {
    namespace = "com.motlagh.core.database"
}

dependencies {
    androidTestImplementation(libs.kotlinx.coroutines.test)
}
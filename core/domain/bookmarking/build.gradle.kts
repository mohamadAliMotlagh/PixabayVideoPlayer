plugins {
    alias(libs.plugins.pixabay.android.library)
}

android {
    namespace = "com.motlagh.core.domain.bookmarking"
}

dependencies {
    implementation(projects.core.database)
    implementation(projects.core.database)
}

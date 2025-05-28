import org.gradle.internal.impldep.org.junit.platform.launcher.EngineFilter.includeEngines

plugins {
    alias(libs.plugins.pixabay.android.feature)
    alias(libs.plugins.pixabay.android.library.compose)
}

android {
    namespace = "com.motlagh.feature.search"

    defaultConfig {
        buildConfigField("String", "PIXABAY_API_KEY", "\"${project.findProperty("PIXABAY_API_KEY") ?: ""}\"")
    }
}
dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(projects.core.testing)
    implementation(projects.core.util)

    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation(projects.core.database)
    implementation(projects.core.domain.video)
    api(projects.core.network)
    api(projects.core.ui)
    implementation(projects.core.domain.bookmarking)
}
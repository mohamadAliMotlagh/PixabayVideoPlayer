plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.motlagh.core.testing"
    compileSdk = 35

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    androidTestImplementation(libs.androidx.junit)
    implementation(libs.hilt.android.testing)
    androidTestImplementation(libs.androidx.espresso.core)
    api(libs.androidx.test.rules)

    api(libs.kotlinx.coroutines.test)
    api(libs.turbine)
    api(libs.junit)
    api(libs.kotlinx.coroutines.test)
    api(libs.mockk)

    api(libs.junit.jupiter.api)
    api(libs.junit.jupiter.engine)
}
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    `kotlin-dsl`
}

group = "com.motlagh.pixabayvideoplayer.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.JVM_17
    }
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)
    compileOnly(libs.room.gradlePlugin)
}

gradlePlugin {
    plugins {
        create("androidApplicationCompose") {
            id = "pixabay.android.application.compose"
            implementationClass = "AndroidApplicationComposeConventionPlugin"
        }

        create("androidApplication") {
            id = "pixabay.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }

        create("androidLibrary") {
            id = "pixabay.android.Library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }

        create("androidLibraryCompose") {
            id = "pixabay.android.Library.Compose"
            implementationClass = "AndroidLibraryComposeConventionPlugin"
        }

        create("hilt") {
            id = "pixabay.hilt"
            implementationClass = "HiltConventionPlugin"
        }

        create("androidRoom") {
            id = "pixabay.android.room"
            implementationClass = "AndroidRoomConventionPlugin"
        }

        create("androidLint") {
            id = "pixabay.android.lint"
            implementationClass = "AndroidLintConventionPlugin"
        }
    }
}

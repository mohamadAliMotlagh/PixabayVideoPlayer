plugins {
    id("java-library")
    alias(libs.plugins.jetbrains.kotlin.jvm)
    alias(libs.plugins.pixabay.android.lint)
}
java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}
kotlin {
    compilerOptions {
        jvmTarget = org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11
    }
}

tasks.withType<Jar> {
    manifest {
        attributes["Lint-Registry-v2"] = "com.motlagh.lint.IssueRegistry"
    }
}

dependencies {
    compileOnly("com.android.tools.lint:lint-api:31.10.0")
    compileOnly("com.android.tools.lint:lint-checks:31.10.0")
}

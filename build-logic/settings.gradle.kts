dependencyResolutionManagement {

    repositories {
        google()
        mavenCentral()
        maven {
            url = uri("https://oss.sonatype.org/content/repositories/snapshots/")
        }
    }

    versionCatalogs {
        create("libs") {
            from(files("../gradle/libs.versions.toml")) // Create this file if not present.
        }
    }
}

rootProject.name = "build-logic"
include(":convention")
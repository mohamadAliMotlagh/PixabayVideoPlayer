pluginManagement {

    includeBuild("build-logic")

    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
        maven {
            url = uri("https://oss.sonatype.org/content/repositories/snapshots/")
        }
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven {
            url = uri("https://oss.sonatype.org/content/repositories/snapshots/")
        }
    }


}



rootProject.name = "PixabayVideoPlayer"

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
include(":app")
include(":lint")
include(":core")
include(":core:designsystem")
include(":feature")
include(":feature:search")
include(":core:testing")

include(":core:mvi")
include(":core:util")
include(":core:database")
include(":core:domain")
include(":core:network")
include(":feature:player")
include(":core:domain:video")
include(":feature:bookmark")
include(":core:domain:bookmarking")
include(":core:ui")

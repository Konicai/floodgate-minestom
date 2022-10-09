enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

rootProject.name = "floodgate-minestom"
include("minestom")
include("core")

pluginManagement {
    repositories {
        gradlePluginPortal()
    }
    plugins {
        id("net.kyori.indra.git") version "2.2.0"
        id("com.github.johnrengelman.shadow") version "7.1.2" // shadowing dependencies
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenCentral()
        maven("https://repo.opencollab.dev/main/") // geyser etc
        maven("https://jitpack.io") // fixes issue with Cloudburst Protocol that geyser depends on
        maven("https://libraries.minecraft.net/") // brigadier
    }
}
include("minestom")

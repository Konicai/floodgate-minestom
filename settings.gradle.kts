enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

rootProject.name = "floodgate-minestom"

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
        mavenCentral() // minestom
        maven("https://repo.opencollab.dev/main/") // floodgate
        maven("https://jitpack.io") // minestom
        maven("https://libraries.minecraft.net/") // brigadier
    }
}

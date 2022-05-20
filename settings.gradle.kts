import groovy.lang.MissingPropertyException

pluginManagement {
    repositories {
        // Standard repositories
        mavenCentral()
        gradlePluginPortal()

        // Architectury
        maven("https://maven.architectury.dev/")
        // Fabric
        maven("https://maven.fabricmc.net/")
        // Essential
        maven("https://repo.essential.gg/repository/maven-public/")
        // Forge
        maven("https://maven.minecraftforge.net/")
        // Jitpack
        maven("https://jitpack.io/")

        // Other
        maven("https://s01.oss.sonatype.org/content/groups/public/")

        // The local repository
        mavenLocal()
    }
}

val projectName = extra["mod.name"]?.toString()
    ?: throw MissingPropertyException("mod.name was not set.")
rootProject.name = projectName

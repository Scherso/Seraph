pluginManagement {
    repositories {
        gradlePluginPortal()
        maven("https://jitpack.io")
    }
}

val projectName: String by settings
rootProject.name = projectName
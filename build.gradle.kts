plugins {
    kotlin("jvm") version "1.8.0"
    id("com.github.weave-mc.weave") version "8b70bcc707"
}

kotlin {
    jvmToolchain(11)
}

val projectName:    String by project
val projectId:      String by project
val projectVersion: String by project
val projectGroup:   String by project
val mcVersion:      String = property("minecraft.version").toString()

version = projectVersion
group   = projectGroup

minecraft.version("1.8.9")

repositories {
    maven("https://jitpack.io")
    maven("https://repo.spongepowered.org/maven/")
}

dependencies {
    compileOnly("com.github.weave-mc:weave-loader:6a9e6a3245")
}
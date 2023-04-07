plugins {
    kotlin("jvm") version "1.8.0"
    id("net.kyori.blossom") version "1.3.1"
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

blossom {
    replaceToken("@VER@", projectVersion)
    replaceToken("@NAME@", projectName)
    replaceToken("@ID@", projectId)
}

minecraft.version("1.8.9")

repositories {
    maven("https://jitpack.io")
}

dependencies {
    compileOnly("com.github.weave-mc:weave-loader:6a9e6a3245")
}

tasks.processResources {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE

    filesMatching("weave.mod.json") {
        expand(
            "id" to projectId,
            "name" to projectName,
            "group" to projectGroup
        )
    }
}

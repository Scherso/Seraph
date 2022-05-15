plugins {
    kotlin("jvm") version("1.6.21")
    id("gg.essential.loom") version("0.10.0.3")
    id("xyz.unifycraft.gradle.tools") version("1.0.1")
    id("xyz.unifycraft.gradle.snippets.shadow") version("1.0.1")
    id("net.kyori.blossom") version("1.3.0")
    java
}

val projectName: String by project
val projectId: String by project
val projectVersion: String by project
val projectGroup: String by project
val mcVersion: String = property("minecraft.version")?.toString() ?: throw IllegalStateException("minecraft.version is not set...")

version = projectVersion
group = projectGroup

loom {
    launchConfigs["client"].arg("--tweakClass", "org.spongepowered.asm.launch.MixinTweaker")
    forge {
        mixinConfig("mixins.${projectId}.json")
        mixin.defaultRefmapName.set("mixins.${projectId}.refmap.json")
    }
}

blossom {
    replaceToken("@VERSION@", projectVersion)
    replaceToken("@NAME@", projectName)
    replaceToken("@ID@", projectId)
}

repositories {
    mavenCentral()
    maven("https://repo.essential.gg/repository/maven-public/")
    maven("https://repo.spongepowered.org/repository/maven-public/")
}

dependencies {
    unishade(libs.vigilance)
    unishade(libs.mixin)
}

tasks {
    processResources {
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
        inputs.property("version", projectVersion)
        inputs.property("mcversion", mcVersion)
        inputs.property("name", projectName)
        inputs.property("id", projectId)

        filesMatching("mcmod.info") {
            expand(
                "id" to projectId,
                "name" to projectName,
                "version" to projectVersion,
                "mcversion" to mcVersion
            )
        }

        filesMatching("mixins.${projectId}.json") {
            expand(
                "id" to projectId
            )
        }
    }

    named<Jar>("jar") {
        archiveBaseName.set(projectName)
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
        manifest.attributes(
            "ModSide" to "CLIENT",
            "ForceloadAsMod" to true,
            "TweakClass" to "org.spongepowered.asm.launch.MixinTweaker",
            "MixinConfigs" to "mixins.${projectId}.json",
            "TweakOrder" to "0"
        )
    }
}

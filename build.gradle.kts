import xyz.unifycraft.gradle.utils.GameSide

plugins {
    kotlin("jvm") version("1.6.21")
    java
    id("gg.essential.loom") version("0.10.0.3")
    id("xyz.unifycraft.gradle.tools") version("1.2.0")
    id("xyz.unifycraft.gradle.tools.shadow") version("1.2.0")
    id("xyz.unifycraft.gradle.tools.blossom") version("1.2.0")
}

loom {
    forge {
        mixin.defaultRefmapName.set("mixins.${modData.id}.refmap.json")
    }
}

loomHelper {
    disableRunConfigs(GameSide.SERVER)
    useForgeMixin(modData.id)
    useTweaker("gg.essential.loader.stage0.EssentialSetupTweaker")
}

val shade by configurations.creating
configurations.implementation.get().extendsFrom(shade)

dependencies {
    unishade("gg.essential:loader-launchwrapper:1.1.3")
    compileOnly(libs.essential)
    compileOnly(libs.mixin)
}

tasks {
    named<Jar>("jar") {
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    }
}

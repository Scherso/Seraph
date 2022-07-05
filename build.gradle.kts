import xyz.unifycraft.gradle.utils.GameSide

plugins {
    kotlin("jvm") version("1.7.0")
    java
    val ucgt = "1.7.0"
    id("xyz.unifycraft.gradle.tools") version(ucgt)
    id("xyz.unifycraft.gradle.tools.loom") version(ucgt)
    id("xyz.unifycraft.gradle.tools.shadow") version(ucgt)
    id("xyz.unifycraft.gradle.tools.blossom") version(ucgt)
}

unifycraft {
    useEssential()
    useDevAuth()
}

loomHelper {
    disableRunConfigs(GameSide.SERVER)
    useForgeMixin(modData.id)
    useMixinRefMap(modData.id)
    useTweaker("gg.essential.loader.stage0.EssentialSetupTweaker")
}

dependencies {
    compileOnly(libs.mixin)
}

tasks {
    named<Jar>("jar") {
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    }
}

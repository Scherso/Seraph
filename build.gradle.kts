import xyz.unifycraft.gradle.utils.GameSide

plugins {
    kotlin("jvm") version("1.6.21")
    java
    id("xyz.unifycraft.gradle.tools") version("1.6.1")
    id("xyz.unifycraft.gradle.tools.loom") version("1.6.1")
    id("xyz.unifycraft.gradle.tools.shadow") version("1.6.1")
    id("xyz.unifycraft.gradle.tools.blossom") version("1.6.1")
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

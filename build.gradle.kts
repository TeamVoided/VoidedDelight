@file:Suppress("PropertyName", "VariableNaming")

import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    alias(libs.plugins.fabric.loom)
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.iridium)
    alias(libs.plugins.iridium.publish)
    alias(libs.plugins.iridium.upload)
}

repositories {
    maven("https://teamvoided.org/releases") { content { includeGroup("org.teamvoided") } }
    maven("https://teamvoided.org/snapshots") { content { includeGroup("org.teamvoided") } }
    maven("https://maven.fzzyhmstrs.me/") { name = "FzzyMaven"; content { includeGroup("me.fzzyhmstrs") } }
    maven("https://maven.terraformersmc.com/") {
        name = "Terraformers"
        content {
            includeGroup("com.terraformersmc")
            includeGroup("dev.emi")
        }
    }
    maven("https://maven.ryanliptak.com")
    maven("https://maven.shedaniel.me/")

    maven("https://maven.greenhouse.lgbt/releases/") { name = "Greenhouse Maven" }
    maven("https://mvn.devos.one/releases/") // Porting Lib
    maven("https://maven.jamieswhiteshirt.com/libs-release") {
        content { includeGroup("com.jamieswhiteshirt") }
    }
    maven("https://api.modrinth.com/maven") { content { includeGroup("maven.modrinth") } }
    maven("https://jitpack.io/")
    mavenCentral()
}

modSettings {
    entrypoint("main", "org.teamvoided.voided_delight.VoidedDelight::init")
    entrypoint("client", "org.teamvoided.voided_delight.VoidedDelightClient::init")
    entrypoint("fabric-datagen", "org.teamvoided.voided_delight.data.gen.VoidedDelightData")

//    dependency("dusk_autumn", "*")
    dependency("farmersdelight", "*")
//    mixinFile("${modId()}.client.mixins.json")
//    mixinFile("${modId()}.mixins.json")
//    accessWidener("${modId()}.accesswidener")
}

dependencies {
    modImplementation(fileTree("libs"))
    modImplementation(libs.farmers.delight)
    modImplementation(libs.dusks.and.dungeons)
    modImplementation(libs.white.pumpkins)
    modImplementation(libs.fzzy.config)

    modImplementation(libs.modmenu)
    modCompileOnly("${libs.emi.get()}:api")
    modLocalRuntime(libs.emi)

    modImplementation(libs.appleskin)
    modImplementation(libs.clothconfig)

    // Testing
    modImplementation(libs.creative.works)
    modImplementation(libs.imguimc)
}
val username = "vDev"
val uuid: String? = null

loom {
    splitEnvironmentSourceSets()
    runs {
        named("client") {
            programArgs("--username", username)
            uuid?.let { programArgs("--uuid", uuid) }
        }

        create("TestWorld") {
            client()
            ideConfigGenerated(true)
            runDir("run")
            programArgs("--quickPlaySingleplayer", "test", "--username", username)
            uuid?.let { programArgs("--uuid", uuid) }
        }

        create("DataGen") {
            client()
            ideConfigGenerated(true)
            vmArg("-Dfabric-api.datagen")
            vmArg("-Dfabric-api.datagen.output-dir=${file("src/main/generated")}")
            vmArg("-Dfabric-api.datagen.modid=${modSettings.modId()}")
            runDir("build/datagen")
        }
    }
}

sourceSets["main"].resources.srcDir("src/main/generated")

tasks {
    val targetJavaVersion = 21
    withType<JavaCompile> {
        options.encoding = "UTF-8"
        options.release.set(targetJavaVersion)
    }

    withType<KotlinCompile>().all {
        compilerOptions.jvmTarget = JvmTarget.JVM_21
    }

    java {
        toolchain.languageVersion.set(JavaLanguageVersion.of(JavaVersion.toVersion(targetJavaVersion).toString()))
        withSourcesJar()
    }
}

publishScript {
    releaseRepository("TeamVoided", "https://maven.teamvoided.org/releases")
    publication(modSettings.modId(), false)
    publishSources(true)
}

uploadConfig {
//    debugMode = true
    modrinthId = ""
    curseId = ""

    // FabricApi
    modrinthDependency("P7dR8mSH", uploadConfig.REQUIRED)
    curseDependency("fabric-api", uploadConfig.REQUIRED)
    // Fabric Language Kotlin
    modrinthDependency("Ha28R6CL", uploadConfig.REQUIRED)
    curseDependency("fabric-language-kotlin", uploadConfig.REQUIRED)

    // TODO
    // Farmers Delight
    modrinthDependency("null", uploadConfig.REQUIRED)
    curseDependency("null-farmersdelight", uploadConfig.REQUIRED)
}

import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jlleitschuh.gradle.ktlint.reporter.ReporterType

plugins {
    kotlin("jvm") version libs.versions.kotlin
    alias(libs.plugins.fabric.loom)
    alias(libs.plugins.ktlint)
}

group = "hiiragi283.ragium"
version = "0.11.0+121x"

repositories {
    mavenCentral()
    maven(url = "https://cursemaven.com") {
        content { includeGroup("curse.maven") }
    }
    maven(url = "https://api.modrinth.com/maven") {
        content { includeGroup("maven.modrinth") }
    }
    maven(url = "https://maven.architectury.dev") // Arch
    maven(url = "https://maven.shedaniel.me") // REI
    maven(url = "https://maven.terraformersmc.com/releases")
    maven(url = "https://server.bbkr.space/artifactory/libs-release") // LibGui
    maven(url = "https://maven.ladysnake.org/releases") // CCA
    maven(url = "https://maven.wispforest.io") // oωo
    maven(url = "https://dl.cloudsmith.io/public/klikli-dev/mods/maven/") // Modonomicon
    maven(url = "https://maven.modmuss50.me/")
    maven(url = "https://maven.blamejared.com") // Patchouli
}

fabricApi {
    configureDataGeneration()
}

loom {
    splitEnvironmentSourceSets()
    mods {
        create("ragium_integrations") {
            sourceSet(sourceSets.main.get())
            sourceSet(sourceSets.getByName("client"))
        }
    }
    runs {
        getByName("client") {
            programArg("--username=Developer")
            vmArg("-Dmixin.debug.export=true")
        }
        getByName("server") {
            runDir = "run/server"
        }
        getByName("datagen") {
            vmArg("-Dfabric-api.datagen")
            vmArg("-Dfabric-api.datagen.output-dir=${file("../ragium/src/main/generated")}")
            vmArg("-Dfabric-api.datagen.modid=ragium_integrations")
        }
    }
}

dependencies {
    minecraft(libs.minecraft)
    mappings("net.fabricmc:yarn:${libs.versions.fabric.yarn.get()}:v2")

    modImplementation(libs.bundles.mods.fabric) {
        exclude(module = "fabric-api")
        exclude(module = "fabric-loader")
    }

    modImplementation(libs.bundles.mods.include) {
        exclude(module = "fabric-api")
        exclude(module = "fabric-loader")
    }
    include(libs.bundles.mods.include) { isTransitive = false }

    modCompileOnly(libs.bundles.mods.compile) {
        exclude(module = "fabric-api")
        exclude(module = "fabric-loader")
    }

    modLocalRuntime(libs.bundles.mods.runtime) {
        exclude(module = "fabric-api")
        exclude(module = "fabric-loader")
    }

    // modImplementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(project(path = ":ragium", configuration = "namedElements")) { isTransitive = false }
    testImplementation("org.jetbrains.kotlin:kotlin-test")
}

kotlin {
    jvmToolchain(21)
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_21)
        freeCompilerArgs.add("-Xjvm-default=all")
    }
}

java {
    withSourcesJar()
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

ktlint {
    version = "1.3.1"
    reporters {
        reporter(ReporterType.HTML)
        reporter(ReporterType.SARIF)
    }
    filter {
        exclude("**/generated/**")
        include("**/kotlin/**")
    }
}

tasks {
    test {
        useJUnitPlatform()
    }

    compileJava {
        options.encoding = "UTF-8"
    }

    processResources {
        inputs.property("version", project.version)
        filesMatching("fabric.mod.json") {
            expand("version" to project.version)
        }
        exclude(".cache")
    }

    jar {
        from("LICENSE") {
            rename { "${it}_${project.base.archivesName.get()}" }
        }
        exclude("**/generated/**")
        exclude("**/unused/**")
    }

    create("runIntegration") {
        dependsOn(":runClient")
    }

    create("runDataIntegration") {
        dependsOn(":runDatagen")
    }

    create("buildIntegration") {
        dependsOn(":build")
    }
}

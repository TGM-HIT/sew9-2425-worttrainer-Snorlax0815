plugins {
    kotlin("jvm") version "2.0.10"
    application
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "tgm.mrafeiner"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    // https://mvnrepository.com/artifact/org.json/json
    implementation("org.json:json:20240303")
    // https://mvnrepository.com/artifact/org.mockito/mockito-core
    testImplementation("org.mockito:mockito-core:5.13.0")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(21)
}

application {
    mainClass.set("tgm.mrafeiner.MainKt")
}

tasks.jar {
    manifest {
        attributes["Main-Class"] = "tgm.mrafeiner.MainKt"
    }
}
tasks.withType<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar> {
    archiveClassifier.set("")
}
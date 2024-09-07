plugins {
    kotlin("jvm") version "2.0.10"
    id("org.jetbrains.dokka") version "1.9.20"
}

subprojects {
    apply(plugin = "org.jetbrains.dokka")
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
    // https://mvnrepository.com/artifact/org.jetbrains.dokka/dokka-gradle-plugin
    implementation("org.jetbrains.dokka:dokka-gradle-plugin:1.9.20")
}

tasks.test {
    useJUnitPlatform()
}
tasks.dokkaHtml{
    outputDirectory.set(layout.buildDirectory.dir("dokka"))
}


kotlin {
    jvmToolchain(21)
}
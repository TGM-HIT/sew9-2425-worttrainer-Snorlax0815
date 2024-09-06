plugins {
    kotlin("jvm") version "2.0.10"
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

}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}
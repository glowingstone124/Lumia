plugins {
    kotlin("jvm") version "2.0.20"
    id("io.ktor.plugin") version "2.3.4"
}

group = "ind.glowingstone.lumia"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

application {
    mainClass.set("ind.glowingstone.lumia.MainKt")
}
dependencies {
    testImplementation(kotlin("test"))
    implementation("io.ktor:ktor-server-core-jvm:2.3.4")
    implementation("io.ktor:ktor-server-netty-jvm:2.3.4")
    implementation("io.ktor:ktor-server-html-builder-jvm:2.3.4")
    implementation("io.ktor:ktor-server-cio-jvm:2.3.4")
    implementation("io.ktor:ktor-server-content-negotiation-jvm:2.3.4")
    implementation("io.ktor:ktor-serialization-jackson-jvm:2.3.4")
    implementation("io.ktor:ktor-server-call-logging-jvm:2.3.4")
    implementation("ch.qos.logback:logback-classic:1.4.11")
}

tasks.test {
    useJUnitPlatform()
}
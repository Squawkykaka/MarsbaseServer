plugins {
    id("java")
    id("io.github.goooler.shadow") version "8.1.7"
}

group = "com.squawkykaka"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("net.minestom:minestom-snapshots:f53625f35b")
    implementation("ch.qos.logback:logback-classic:1.5.6")
    implementation("de.articdive:jnoise-pipeline:4.1.0")
}

tasks.withType<Jar> {
    manifest {
        // Change this to your main class
        attributes["Main-Class"] = "org.squawkykaka.Main"
    }
}
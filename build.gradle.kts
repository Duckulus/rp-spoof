plugins {
    id("fabric-loom") version "1.3-SNAPSHOT"
}

group = "de.duckulus"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    minecraft("com.mojang:minecraft:1.20.1")
    mappings(loom.officialMojangMappings())
    modImplementation("net.fabricmc:fabric-loader:0.14.22")
    modImplementation("net.fabricmc.fabric-api:fabric-api:0.87.0+1.20.1")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

tasks.test {
    useJUnitPlatform()
}

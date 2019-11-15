plugins {
    `java-gradle-plugin`
    `kotlin-dsl`
//    kotlin("jvm") version "1.3.50"
    id("com.gradle.plugin-publish") version "0.10.0"

}

repositories {
    jcenter()
    mavenCentral()
}

group = "io.ezet.gradle.substitute"
version = "0.1"



pluginBundle {
    website = "https://github.com/ezet/gradle-substitute-for-source"
    vcsUrl = "https://github.com/ezet/gradle-substitute-for-source"
    tags = listOf("substitute", "dependency", "local", "source", "development")
}

gradlePlugin {
    plugins {
        register("io.ezet.gradle.substitute.substitute-source") {
            id = "io.ezet.gradle.substitute.substitute-source"
            displayName = "Substitute if available"
            description = "Substitute a dependency with a local module if it is loaded."
            implementationClass = "io.ezet.gradle.substitute.LocalRemoteDependencyPlugin"
        }
        register("io.ezet.gradle.substitute.include-if-exists") {
            id = "io.ezet.gradle.substitute.include-if-exists"
            displayName = "Include if available"
            description = "Include a module if it is available on a path specified in local.properties."
            implementationClass = "io.ezet.gradle.substitute.IncludeIfExistsPlugin"

        }
    }
}


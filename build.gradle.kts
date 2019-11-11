plugins {
    `java-gradle-plugin`
    id("com.gradle.plugin-publish")  version "0.10.0"
}

group = "io.ezet.gradle.substitute"
version = "0.1"



pluginBundle {
    website = "<substitute your project website>"
    vcsUrl = "https://github.com/ezet/gradle-substitute-for-source"
    tags = listOf("substitute", "dependency", "local", "source", "development")
}

gradlePlugin {
    plugins {
        create("substitutePlugin") {
            id = "io.ezet.substituteForSource"
            displayName = "Substitute for Source"
            description = "Replace dependencies with local source if defined and available."
            implementationClass = "io.ezet.gradle.substitute.IncludeIfExistsPlugin"
        }
    }
}


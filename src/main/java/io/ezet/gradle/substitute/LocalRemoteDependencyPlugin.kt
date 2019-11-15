package io.ezet.gradle.substitute

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.component.ModuleComponentSelector
import org.gradle.kotlin.dsl.create

/***
 * Plugin to replace remote dependencies with local versions if possible.
 */
class LocalRemoteDependencyPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        target.extensions.create<LocalRemoteDependencyExtension>("substitutions", target)
    }
}

open class LocalRemoteDependencyExtension(private val project: Project) {

    /***
     * Substitute a dependency with a local module.
     */
    fun substitute(group: String, module: String? = null) {
        project.configurations.all { configuration ->
            configuration.resolutionStrategy.dependencySubstitution.all inner@{
                val requested = this.requested as? ModuleComponentSelector ?: return@inner
                if (requested.group == group && (module == null || requested.module == module)) {
                    println("Attempting substitution for: $requested")
                    val targetProject = project.findProject(":${requested.module}")
                    if (targetProject != null) {
                        println("Substitution success: $targetProject")
                        this.useTarget(targetProject)
                    }
                }
            }
            return
        }
    }
}


package io.ezet.gradle.substitute

import org.gradle.api.Plugin
import org.gradle.api.initialization.Settings
import org.gradle.kotlin.dsl.create
import java.io.File

/***
 * Plugin that can be used to conditionally include modules if a path is defined,
 * and the`  module is available at the given path.
 *
 */
class IncludeIfExistsPlugin : Plugin<Settings> {

    private lateinit var settings: Settings
    override fun apply(target: Settings) {
        settings = target
        target.extensions.create<IncludeExtension>("ifExists", settings)
    }
}

open class IncludeExtension(private val settings: Settings) {
    /***
     * Use this method to define a module to be included if it exists locally.
     *
     */
    fun tryInclude(propertyName: String, subPath: String, module: String) {
        includeIfExists(propertyName, subPath, module)
    }

    private fun includeIfExists(propertyName: String, subPath: String, module: String) {
        val localProperties = File("local.properties")
        if (!localProperties.exists()) return
        val properties = java.util.Properties()
        properties.load(localProperties.inputStream())
        val property = properties.getProperty(propertyName) ?: return
        val path = ("$property${File.separator}$subPath")
        val file = File(path)
        if (file.exists()) {
            println("Including module: $path $module")
            settings.include(module)
            settings.project(":$module").projectDir = file
        } else {
            println("Module: $module with provided path: $path doesn't exist")
        }
    }
}

pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    plugins {
        id ("com.android.application") version "7.1.0-alpha11"
        id ("com.android.library")version "7.1.0-alpha11"
        id ("org.jetbrains.kotlin.android") version "1.6.21"
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

include (":app")
rootProject.name = "AiVoiceApp"
rootProject.buildFileName = "build.gradle.kts"
include(":lib_base")
include(":lib_network")
include(":lib_voice")
include(":module_app_manager")
include(":module_constellation")
include(":module_developer")
include(":module_joke")
include(":module_map")
include(":module_setting")
include(":module_weather")
include(":module_voice_setting")

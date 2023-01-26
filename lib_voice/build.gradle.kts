plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")version "1.4.20"

}

android {
    compileSdk = AppConfig.compileSdkVersion
    buildToolsVersion = AppConfig.buildToolsVersion

    defaultConfig {
        minSdk = AppConfig.minSdkVersion
        targetSdk = AppConfig.targetSdkVersion

        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles (
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}
//ARouter
kapt {
    arguments {
        arg("AROUTER_MODULE_NAME", project.name)
    }
}
dependencies {
    api (fileTree(mapOf("dir" to "libs" , "include" to listOf("*.jar"))))
    api ("com.google.android.material:material:1.4.0")
    //运行时注释
    annotationProcessor(DependenciesConfig.AROUTER_COMPILER)

    //百度tts库
    api(files("libs/bdtts.jar"))
    //百度语音唤醒
    api(files("libs/bdasr.jar"))
    api(DependenciesConfig.RETROFIT_GSON)

}
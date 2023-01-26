plugins {
    if (ModuleConfig.isApp){
        id("com.android.application")
    }else{
        id("com.android.library")
    }
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")version "1.4.20"

}

android {
    compileSdk = 30
    buildToolsVersion = AppConfig.buildToolsVersion

    defaultConfig {
        println("ModuleConfig.isApp = "+ModuleConfig.isApp)

        if (ModuleConfig.isApp){
            println("ModuleConfig.isApp = "+ModuleConfig.isApp)

//            applicationId = ModuleConfig.MODULE_APP_MANAGER

        }
        minSdk = 21
        targetSdk = 30

        //ARouter
        kapt {
            arguments {
                arg("AROUTER_MODULE_NAME", project.name)
            }
        }

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
//        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
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

    //动态替换资源
    sourceSets{
        getByName("main"){
            println(ModuleConfig.isApp)
            if(ModuleConfig.isApp){
                manifest.srcFile("src/main/manifest/AndroidManifest.xml")
            }else{
                manifest.srcFile("src/main/AndroidManifest.xml")
            }
        }
    }
}



dependencies {
    implementation (fileTree(mapOf("dir" to "libs" , "include" to listOf("*.jar"))))
    implementation (project(":lib_base"))
    //运行时注释
    kapt(DependenciesConfig.AROUTER_COMPILER)
}
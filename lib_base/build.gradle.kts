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
//ARouter
        kapt {
            arguments {
                arg("AROUTER_MODULE_NAME", project.name)
            }
        }
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

dependencies {
    implementation (fileTree(mapOf("dir" to "libs" , "include" to listOf("*.jar"))))
    implementation ("com.google.android.material:material:1.4.0")
    api (DependenciesConfig.KTX_CORE)
    api (DependenciesConfig.APP_COMPAT)
    api(DependenciesConfig.STD_LIB)
    api(DependenciesConfig.EVENT_BUS)
    //AROUTER
    api(DependenciesConfig.AROUTER)
    //RecyclerView
    api(DependenciesConfig.RECYCLERVIEW)
    api(DependenciesConfig.LOTTIE)
    //AndPermissions
//    api(DependenciesConfig.AND_PERMISSIONS)

    //刷新
    api(DependenciesConfig.REFRESH_KERNEL)
    api(DependenciesConfig.REFRESH_HEADER)
    api(DependenciesConfig.REFRESH_FOOT)

    api(project(":lib_voice"))
    api(project(":lib_network"))

//    //运行时注释
//    kapt(DependenciesConfig.AROUTER_COMPILER)
}
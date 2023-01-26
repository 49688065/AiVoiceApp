object KotlinConstants {
    //Gradle 版本
    const val gradle_version = "3.6.2"
    //Kotlin 版本
//    const val kotlin_version = "1.3.71"
    const val kotlin_version = "1.5.21"
}

object AppConfig {
    const val compileSdkVersion = 29
    const val buildToolsVersion = "29.02"
    var applicationId = "com.imooic.aivoiceapp"
    const val minSdkVersion = 21
    const val targetSdkVersion = 29
    const val versionCode = 1
    const val versionName = "1.0"

}

object DependenciesConfig{
    const val KTX_CORE="androidx.core:core-ktx:1.6.0"
    //android标准库
    const val APP_COMPAT="androidx.appcompat:appcompat:1.3.0"
    //kotlin基础库
    const val STD_LIB="org.jetbrains.kotlin:kotlin-stdlib-jdk7:${KotlinConstants.kotlin_version}"

    //eventBus
    const val EVENT_BUS = "org.greenrobot:eventbus:3.2.0"

    //ARouter
    const val AROUTER = "com.alibaba:arouter-api:1.5.2"
    const val AROUTER_COMPILER = "com.alibaba:arouter-compiler:1.5.2"

    //RecyclerView
    const val RECYCLERVIEW = "androidx.recyclerview:recyclerview:1.2.0-alpha01"

    const val AND_PERMISSIONS = "com.yanzhenjie:permission:2.0.3"

    //Retrofit
    const val RETROFIT = "com.squareup.retrofit2:retrofit:2.8.1"
    const val RETROFIT_GSON = "com.squareup.retrofit2:converter-gson:2.8.1"

    //ViewPager
//    const val VIEWPAGER = "com.zhy:magic-viewpager:1.0.1"

    //lottie动画
    const val LOTTIE = "com.airbnb.android:lottie:5.2.0"

    //刷新
    const val REFRESH_KERNEL = "io.github.scwang90:refresh-layout-kernel:2.0.5"
    const val REFRESH_HEADER = "io.github.scwang90:refresh-header-classics:2.0.5"
    const val REFRESH_FOOT = "io.github.scwang90:refresh-footer-classics:2.0.5"

    //图表
    const val CHART = "com.github.PhilJay:MPAndroidChart:v3.1.0"
}

object ModuleConfig{
    //module是否app
     var isApp = false

    const val MODULE_APP_MANAGER = "com.imooic.module_app_manager"
    const val MODULE_CONSTELLATION = "com.imooic.module_constellation"
    const val MODULE_DEVELOPER = "com.imooic.module_developer"
    const val MODULE_JOKE = "com.imooic.module_joke"
    const val MODULE_MAP = "com.imooic.module_map"
    const val MODULE_SETTING = "com.imooic.module_setting"
    const val MODULE_VOICE_SETTING = "com.imooic.module_voice_setting"
    const val MODULE_WEATHER = "com.imooic.module_weather"
}
object Plugins {
    // plugins
    const val androidApplication = "com.android.application"
    const val androidLibrary = "com.android.library"
    const val kotlinAndroid = "android"
    const val kotlin_Android = "kotlin-android"
    const val kotlinParcelize = "kotlin-parcelize"
    const val kotlinExtension = "kotlin-android-extensions"
    const val kotlinKapt = "kapt"
    const val daggerHilt = "dagger.hilt.android.plugin"
    const val navigationSafeArgs = "androidx.navigation.safeargs"
    const val kotlinNavigationSafeArgs = "androidx.navigation.safeargs.kotlin"

}
object BuildType{
    const val release = "release"
    const val debug = "debug"
}
object ProGuard{
    const val file = "proguard-android-optimize.txt"
    const val rule = "proguard-rules.pro"
}
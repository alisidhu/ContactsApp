

object GradlePlugins {
    const val Android = "com.android.tools.build:gradle:${Versions.gradle}"
    const val Kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    const val hilt = "com.google.dagger:hilt-android-gradle-plugin:${Versions.daggerHilt}"
    const val navigationSafeArgs = "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigationVersion}"

}

object TestDependencies {
    object TestImplementation{
        const val junit = "junit:junit:${Versions.junitVersion}"

    }
    object AndroidTestImplementation{
        const val testJunit = "androidx.test.ext:junit:${Versions.testJunitVersion}"
        const val testRunner = "androidx.test:runner:${Versions.testRunner}"
        const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espressoCoreVersion}"

    }

}


object AndroidXDependencies {
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompatVersion}"
    const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtxVersion}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayoutVersion}"
    const val constraintLayoutSolver = "androidx.constraintlayout:constraintlayout-solver:${Versions.constraintLayoutSolver}"

    const val viewpager2 = "androidx.viewpager2:viewpager2:${Versions.viewpager2}"
    const val navigationFragmentKtx = "androidx.navigation:navigation-fragment-ktx:${Versions.navigationVersion}"
    const val navigationUItKtx = "androidx.navigation:navigation-ui-ktx:${Versions.navigationVersion}"
    const val navigationSafeArgs = "androidx.navigation:navigation-safe-args-gradle-plugin::${Versions.navigationVersion}"
    const val cardView = "androidx.cardview:cardview:${Versions.cardview}"
    const val recyclerview = "androidx.recyclerview:recyclerview:${Versions.recyclerview}"


}
object LifeCycleDependencies {
    const val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime:${Versions.arch}"
    const val lifecycleCommonJava = "androidx.lifecycle:lifecycle-common-java8:${Versions.arch}"
    const val lifecycleExtensions = "androidx.lifecycle:lifecycle-extensions:${Versions.arch}"
    const val liveDataBuilder = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.arch}"
}
object DaggerDependencies {
    //https://developer.android.com/training/dependency-injection/hilt-android#kts
    //https://developer.android.com/training/dependency-injection/hilt-jetpack#kotlin

    const val hiltViewModel = "androidx.hilt:hilt-lifecycle-viewmodel:${Versions.hiltVersion}"
    const val daggerHilt = "com.google.dagger:hilt-android:${Versions.daggerHilt}"
    const val daggerHiltFragment = "androidx.hilt:hilt-navigation-fragment:${Versions.hiltVersion}"

    const val hiltCompiler = "androidx.hilt:hilt-compiler:${Versions.hiltVersion}"
    const val daggerHiltCompiler = "com.google.dagger:hilt-android-compiler:${Versions.daggerHilt}"
}
object RoomDependencies {
    const val roomRunTime = "androidx.room:room-runtime:${Versions.room_version}"
    //annotationProcessor
    const val roomCompiler = "androidx.room:room-compiler:${Versions.room_version}"
    // To use Kotlin annotation processing tool (kapt)
    const val roomCompilerKapt = "androidx.room:room-compiler:${Versions.room_version}"
    // To use Kotlin Symbolic Processing (KSP)
    const val roomCompilerKsp = "androidx.room:room-compiler:${Versions.room_version}"
    // optional - Kotlin Extensions and Coroutines support for Room
    const val roomExtensions = "androidx.room:room-ktx:${Versions.room_version}"
}
object JetbrainsDependencies {

    const val kotlinStdlib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlinVersion}"
    const val kotlinReflect = "org.jetbrains.kotlin:kotlin-reflect:${Versions.kotlinVersion}"
    const val coroutineCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.kotlinCoroutine}"
    const val coroutineAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.kotlinCoroutine}"
}
object SplashScreenDependencies {
    const val splashScreen = "androidx.core:core-splashscreen:${Versions.splashScreen}"
}

object CommonDependencies {

    const val androidSdp = "com.intuit.sdp:sdp-android:${Versions.sdpLibrary}"
    const val androidSsp = "com.intuit.ssp:ssp-android:${Versions.sdpLibrary}"
    const val dataBindingCompiler = "com.android.databinding:compiler:${Versions.dataBindingCompiler}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val glideTransformation = "com.github.bumptech.glide:glide:${Versions.glideTransformation}"
    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"
    const val sharedPref = "androidx.security:security-crypto:${Versions.encryptedSharedPref}"
    const val easyPermissions = "pub.devrel:easypermissions:${Versions.easyPermissions}"

}

import org.jetbrains.kotlin.kapt3.base.Kapt.kapt

plugins {
    id(Plugins.androidLibrary)
    id("org.jetbrains.kotlin.android")
    id(Plugins.kotlinParcelize)
    kotlin(Plugins.kotlinKapt)
    id(Plugins.daggerHilt)
    id("kotlin-kapt")
}

android {

    compileSdk = Config.compileSdkVersion
    defaultConfig {
        minSdk = Config.minSdkVersion
        targetSdk = Config.targetSDKVersion
        testInstrumentationRunner = Config.androidTestInstrumentation
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
}

dependencies {

    testImplementation (TestDependencies.TestImplementation.junit)
    androidTestImplementation (TestDependencies.AndroidTestImplementation.testJunit)
    androidTestImplementation (TestDependencies.AndroidTestImplementation.testRunner)
    androidTestImplementation (TestDependencies.AndroidTestImplementation.espressoCore)
    //DaggerDependencies
    implementation(DaggerDependencies.daggerHilt)
    kapt(DaggerDependencies.daggerHiltCompiler)
    kapt(DaggerDependencies.hiltCompiler)
    kapt(CommonDependencies.dataBindingCompiler)

    //RoomDependencies
    implementation(RoomDependencies.roomRunTime)
    kapt(RoomDependencies.roomCompilerKapt)
    annotationProcessor(RoomDependencies.roomCompiler)
    implementation(RoomDependencies.roomExtensions)
}
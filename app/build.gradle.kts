plugins {
    id(Plugins.androidApplication)
    id(Plugins.kotlinParcelize)
    kotlin(Plugins.kotlinAndroid)
    kotlin(Plugins.kotlinKapt)
    id(Plugins.daggerHilt)
    id("kotlin-kapt")
    id(Plugins.kotlinNavigationSafeArgs)

}
android {
    compileSdk = Config.compileSdkVersion
    buildToolsVersion = Config.buildToolsVersion
    defaultConfig {
        applicationId = Config.applicationId
        minSdk = Config.minSdkVersion
        targetSdk = Config.targetSDKVersion
        versionCode = Config.versionCode
        versionName = Config.versionName
        testInstrumentationRunner = Config.androidTestInstrumentation
    }

    buildTypes {
        getByName(BuildType.debug) {
            isMinifyEnabled = false
            proguardFiles(
                    getDefaultProguardFile(ProGuard.file),
                    ProGuard.rule
            )
        }
        getByName(BuildType.release) {
            isMinifyEnabled = false
            applicationIdSuffix = ".development"
            isDebuggable = true

        }
    }
    packagingOptions {
        jniLibs.excludes.add("META-INF/notice.txt")
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
    buildFeatures {
        dataBinding = true
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    testImplementation (TestDependencies.TestImplementation.junit)
    androidTestImplementation (DependenciesManager.testImplementation)
    api(project(":database"))

    //AndroidX
    implementation(SplashScreenDependencies.splashScreen)
    implementation(DependenciesManager.androidXImplementation)

    //LifeCycleDependencies
    implementation(DependenciesManager.lifeCycleKtxImplementation)
    //JetbrainsDependencies
    implementation(DependenciesManager.jetbrainsDependenciesImplementation)
    //CommonDependencies
    implementation(DependenciesManager.commonImplementation)
    //DaggerDependencies
    implementation(DaggerDependencies.daggerHilt)
    kapt(DaggerDependencies.daggerHiltCompiler)
    kapt(DaggerDependencies.hiltCompiler)
    kapt(CommonDependencies.dataBindingCompiler)
   // kapt ("com.android.databinding:compiler:3.2.0-alpha10")
    //RoomDependencies
    implementation(RoomDependencies.roomRunTime)
    kapt(RoomDependencies.roomCompilerKapt)
    annotationProcessor(RoomDependencies.roomCompiler)
    implementation(RoomDependencies.roomExtensions)
}
kapt {
    correctErrorTypes = true
    generateStubs = true

}
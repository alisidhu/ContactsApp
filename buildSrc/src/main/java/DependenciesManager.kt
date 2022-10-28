import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.api.initialization.dsl.ScriptHandler.CLASSPATH_CONFIGURATION

object DependenciesManager {
    val testImplementation = arrayListOf<String>().apply {
        add(TestDependencies.AndroidTestImplementation.testJunit)
        add(TestDependencies.AndroidTestImplementation.testRunner)
        add(TestDependencies.AndroidTestImplementation.espressoCore)
    }
    val androidXImplementation = arrayListOf<String>().apply {
        add(AndroidXDependencies.appCompat)
        add(AndroidXDependencies.coreKtx)
        add(AndroidXDependencies.constraintLayout)
        add(AndroidXDependencies.constraintLayoutSolver)
        add(AndroidXDependencies.viewpager2)
        add(AndroidXDependencies.navigationFragmentKtx)
        add(AndroidXDependencies.navigationUItKtx)
        add(AndroidXDependencies.cardView)
        add(AndroidXDependencies.recyclerview)
        //add(AndroidXDependencies.navigationSafeArgs)
    }
    val lifeCycleKtxImplementation = arrayListOf<String>().apply {
        add(LifeCycleDependencies.lifecycleRuntime)
        add(LifeCycleDependencies.lifecycleCommonJava)
        add(LifeCycleDependencies.lifecycleExtensions)
        add(LifeCycleDependencies.liveDataBuilder)
    }

    val jetbrainsDependenciesImplementation = arrayListOf<String>().apply {
        add(JetbrainsDependencies.kotlinStdlib)
        add(JetbrainsDependencies.kotlinReflect)
        add(JetbrainsDependencies.coroutineCore)
        add(JetbrainsDependencies.coroutineCore)
        add(JetbrainsDependencies.coroutineAndroid)
    }
    val commonImplementation = arrayListOf<String>().apply {
        add(CommonDependencies.androidSdp)
        add(CommonDependencies.androidSsp)
        add(CommonDependencies.material)
        add(CommonDependencies.glideTransformation)
        add(CommonDependencies.timber)
        add(CommonDependencies.sharedPref)
        add(CommonDependencies.easyPermissions)
    }

}


fun DependencyHandler.implementation(list: List<String>) {
    list.forEach { dependency ->
        add("implementation", dependency)
    }
}

fun DependencyHandler.androidTestImplementation(list: List<String>) {
    list.forEach { dependency ->
        add("androidTestImplementation", dependency)
    }
}

fun DependencyHandler.testImplementation(list: List<String>) {
    list.forEach { dependency ->
        add("testImplementation", dependency)
    }
}

fun DependencyHandler.classpath(list: List<String>) {
    list.forEach { classPath ->
        this.add(CLASSPATH_CONFIGURATION, classPath)
    }
}
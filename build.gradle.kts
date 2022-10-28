buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(GradlePlugins.Android)
        classpath(GradlePlugins.Kotlin)
        classpath(GradlePlugins.hilt)
        classpath(GradlePlugins.navigationSafeArgs)
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.10")
    }

}
tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
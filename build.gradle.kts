buildscript {
    dependencies {
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.44")
    }
}

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.jetbrainsKotlinAndroid) apply false
    id("androidx.room") version "2.6.1" apply false

    // alias(libs.plugins.daggerHiltAndroid) apply false
    // alias(libs.plugins.kotlinKapt) apply false
    // id("com.google.devtools.ksp") version "1.9.0-1.0.13" apply false

}
tasks.register("clean", Delete::class) {
    delete(rootProject.layout.buildDirectory)
}
// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath (src.main.kotlin.BuildPlugins.androidGradlePlugin)
        classpath (src.main.kotlin.BuildPlugins.kotlinGradlePlugin)
        classpath (src.main.kotlin.BuildPlugins.androidSafeArgs)
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}

tasks.register("clean").configure {
    delete("build")
}
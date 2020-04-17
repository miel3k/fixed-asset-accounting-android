object Plugins {

    object Versions {
        const val kotlin = "1.3.71"
        const val buildTools = "3.6.2"
        const val safeArgs = "2.2.1"
    }

    const val androidGradlePlugin = "com.android.tools.build:gradle:${Versions.buildTools}"
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    const val androidSafeArgs =
        "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.safeArgs}"
    const val androidApplication = "com.android.application"
    const val kotlinAndroid = "kotlin-android"
    const val kotlinAndroidExtensions = "kotlin-android-extensions"
    const val kotlinKapt = "kotlin-kapt"
    const val kotlinAndroidSafeArgs = "androidx.navigation.safeargs.kotlin"
}

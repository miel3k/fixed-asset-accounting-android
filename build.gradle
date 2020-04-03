// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    ext.kotlin_version = '1.3.71'
    ext.gradle_version = '3.6.2'
    ext.safe_args_version = '2.2.1'
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath "com.android.tools.build:gradle:$gradle_version"
        classpath "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version"
        classpath "androidx.navigation:navigation-safe-args-gradle-plugin:$safe_args_version"
    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}

ext {
    app_compat_version = '1.1.0'
    core_ktx_version = '1.2.0'
    constraint_layout_version = '1.1.3'
    lifecycle_version = '2.2.0'
    navigation_version = '2.2.1'
    room_version = '2.2.5'
    koin_version = '2.0.1'
    junit_version = '4.13'
    junit_ext_version = '1.1.1'
    espresso_version = '3.2.0'
    barcode_scanner_version = '1.9.8'
    dexter_permissions_version = '6.0.2'
}

task clean(type: Delete) {
    delete rootProject.buildDir
}

plugins {
    id(Plugins.androidDynamicFeature)
    id(Plugins.kotlinAndroid)
    id(Plugins.kotlinAndroidExtensions)
    id(Plugins.kotlinAndroidSafeArgs)
}

android {
    compileSdkVersion(AndroidConfiguration.compileSdkVersion)
    defaultConfig {
        minSdkVersion(AndroidConfiguration.minSdkVersion)
        targetSdkVersion(AndroidConfiguration.targetSdkVersion)
        versionCode = AndroidConfiguration.versionCode
        versionName = AndroidConfiguration.versionName
    }
    buildTypes {
        getByName(AndroidConfiguration.BuildType.Release.name) {
            isMinifyEnabled = AndroidConfiguration.BuildType.Release.isMinifyEnabled
            proguardFiles("proguard-android-optimize.txt", "proguard-rules.pro")
        }
        getByName(AndroidConfiguration.BuildType.Debug.name) {
            isMinifyEnabled = AndroidConfiguration.BuildType.Debug.isMinifyEnabled
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
}

dependencies {
    implementation(project(Modules.APP))
    implementation(Libraries.barcodeScanner)
}

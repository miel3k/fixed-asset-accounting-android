plugins {
    id(Plugins.androidLibrary)
    id(Plugins.kotlinAndroid)
    id(Plugins.kotlinAndroidExtensions)
}

android {
    compileSdkVersion(AndroidConfiguration.compileSdkVersion)
    defaultConfig {
        minSdkVersion(AndroidConfiguration.minSdkVersion)
        targetSdkVersion(AndroidConfiguration.targetSdkVersion)

        versionCode = AndroidConfiguration.versionCode
        versionName = AndroidConfiguration.versionName
        testInstrumentationRunner = AndroidConfiguration.testInstrumentationRunner
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

    testOptions {
        unitTests.isReturnDefaultValues = true
    }
}

dependencies {
    api(Libraries.kotlinStdLib)
    api(Libraries.kotlinReflect)
    api(Libraries.kodein)
    api(Libraries.kodeinAndroidX)
    api(Libraries.appCompat)
    api(Libraries.coreKtx)
    api(Libraries.navigationFragmentKtx)
    api(Libraries.lifecycleExtensions)
    api(Libraries.viewModelKtx)
    api(Libraries.coroutinesAndroid)
}

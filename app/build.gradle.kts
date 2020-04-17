plugins {
    id(src.main.kotlin.BuildPlugins.androidApplication)
    id(src.main.kotlin.BuildPlugins.kotlinAndroid)
    id(src.main.kotlin.BuildPlugins.kotlinAndroidExtensions)
    id(src.main.kotlin.BuildPlugins.kotlinKapt)
    id(src.main.kotlin.BuildPlugins.kotlinAndroidSafeArgs)
}

android {
    compileSdkVersion(src.main.kotlin.AndroidConfiguration.compileSdkVersion)
    defaultConfig {
        applicationId = src.main.kotlin.AndroidConfiguration.applicationId
        minSdkVersion(src.main.kotlin.AndroidConfiguration.minSdkVersion)
        targetSdkVersion(src.main.kotlin.AndroidConfiguration.targetSdkVersion)
        buildToolsVersion(src.main.kotlin.AndroidConfiguration.buildToolsVersion)
        versionCode = src.main.kotlin.AndroidConfiguration.versionCode
        versionName = src.main.kotlin.AndroidConfiguration.versionName
        testInstrumentationRunner = src.main.kotlin.AndroidConfiguration.testInstrumentationRunner
    }
    buildTypes {
        getByName(src.main.kotlin.AndroidConfiguration.BuildType.Release.name) {
            isMinifyEnabled = src.main.kotlin.AndroidConfiguration.BuildType.Release.isMinifyEnabled
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
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
}

dependencies {
    implementation(src.main.kotlin.Libraries.kotlinStdLib)
    implementation(src.main.kotlin.Libraries.appCompat)
    implementation(src.main.kotlin.Libraries.ktxCore)
    implementation(src.main.kotlin.Libraries.constraintLayout)
    implementation(src.main.kotlin.Libraries.lifecycleExtensions)
    implementation(src.main.kotlin.Libraries.viewModelKtx)
    implementation(src.main.kotlin.Libraries.navigationRuntimeKtx)
    implementation(src.main.kotlin.Libraries.navigationFragmentKtx)
    implementation(src.main.kotlin.Libraries.navigationUiKtx)
    implementation(src.main.kotlin.Libraries.navigationCommonKtx)
    implementation(src.main.kotlin.Libraries.roomRuntime)
    implementation(src.main.kotlin.Libraries.roomKtx)
    implementation(src.main.kotlin.Libraries.koinAndroid)
    implementation(src.main.kotlin.Libraries.koinScope)
    implementation(src.main.kotlin.Libraries.koinViewModel)
    implementation(src.main.kotlin.Libraries.barcodeScanner)
    implementation(src.main.kotlin.Libraries.dexterPermissions)

    testImplementation(src.main.kotlin.TestLibraries.junit)
    testImplementation(src.main.kotlin.TestLibraries.room)
    androidTestImplementation(src.main.kotlin.TestLibraries.junitExt)
    androidTestImplementation(src.main.kotlin.TestLibraries.testRunner)
    androidTestImplementation(src.main.kotlin.TestLibraries.espresso)
}
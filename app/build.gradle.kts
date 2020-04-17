plugins {
    id(Plugins.androidApplication)
    id(Plugins.kotlinAndroid)
    id(Plugins.kotlinAndroidExtensions)
    id(Plugins.kotlinKapt)
    id(Plugins.kotlinAndroidSafeArgs)
}

android {
    compileSdkVersion(AndroidConfiguration.compileSdkVersion)
    defaultConfig {
        applicationId = AndroidConfiguration.applicationId
        minSdkVersion(AndroidConfiguration.minSdkVersion)
        targetSdkVersion(AndroidConfiguration.targetSdkVersion)
        buildToolsVersion(AndroidConfiguration.buildToolsVersion)
        versionCode = AndroidConfiguration.versionCode
        versionName = AndroidConfiguration.versionName
        testInstrumentationRunner = AndroidConfiguration.testInstrumentationRunner
    }
    buildTypes {
        getByName(AndroidConfiguration.BuildType.Release.name) {
            isMinifyEnabled = AndroidConfiguration.BuildType.Release.isMinifyEnabled
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    dynamicFeatures = Modules.getDynamicFeatureModules().toMutableSet()
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
}

dependencies {
    implementation(Libraries.kotlinStdLib)
    implementation(Libraries.appCompat)
    implementation(Libraries.coreKtx)
    implementation(Libraries.constraintLayout)
    implementation(Libraries.lifecycleExtensions)
    implementation(Libraries.viewModelKtx)
    implementation(Libraries.navigationRuntimeKtx)
    implementation(Libraries.navigationFragmentKtx)
    implementation(Libraries.navigationUiKtx)
    implementation(Libraries.navigationCommonKtx)
    implementation(Libraries.roomRuntime)
    implementation(Libraries.roomKtx)
    implementation(Libraries.barcodeScanner)
    implementation(Libraries.dexterPermissions)

    testImplementation(TestLibraries.junit)
    testImplementation(TestLibraries.room)
    androidTestImplementation(TestLibraries.junitExt)
    androidTestImplementation(TestLibraries.testRunner)
    androidTestImplementation(TestLibraries.espresso)
}
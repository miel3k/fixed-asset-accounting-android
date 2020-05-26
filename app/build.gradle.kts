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
        buildConfigField("FEATURE_MODULE_NAMES", getDynamicFeatureModuleNames())
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
    api(project(Modules.CORE))
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
    api(Libraries.retrofit)
    api(Libraries.retrofitMoshiConverter)

    testImplementation(TestLibraries.junit)
    androidTestImplementation(TestLibraries.junitExt)
    androidTestImplementation(TestLibraries.testRunner)
    androidTestImplementation(TestLibraries.espresso)
}

fun getDynamicFeatureModuleNames() = Modules.getDynamicFeatureModules()
    .map { it.replace(":feature_", "") }
    .toSet()

fun com.android.build.gradle.internal.dsl.DefaultConfig.buildConfigField(
    name: String,
    value: Set<String>
) {
    val strValue =
        value.joinToString(prefix = "{", separator = ",", postfix = "}", transform = { "\"$it\"" })
    buildConfigField("String[]", name, strValue)
}
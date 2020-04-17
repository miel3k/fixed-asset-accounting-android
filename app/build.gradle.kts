apply plugin: 'com.android.application'
apply plugin: 'kotlin-android'
apply plugin: 'kotlin-android-extensions'
apply plugin: 'kotlin-kapt'
apply plugin: 'androidx.navigation.safeargs.kotlin'

android {
    compileSdkVersion 29
    buildToolsVersion "29.0.3"
    defaultConfig {
        applicationId "com.tp.fixedassetaccounting"
        minSdkVersion 21
        targetSdkVersion 29
        versionCode 1
        versionName "1.0"
        testInstrumentationRunner "androidx.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
        }
    }
    compileOptions {
        sourceCompatibility = 1.8
        targetCompatibility = 1.8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation fileTree(dir: 'libs', include: ['*.jar']),
            "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlin_version",
            "androidx.appcompat:appcompat:$app_compat_version",
            "androidx.core:core-ktx:$core_ktx_version",
            "androidx.constraintlayout:constraintlayout:$constraint_layout_version",
            "androidx.lifecycle:lifecycle-extensions:$lifecycle_version",
            "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version",
            "androidx.navigation:navigation-runtime-ktx:$navigation_version",
            "androidx.navigation:navigation-fragment-ktx:$navigation_version",
            "androidx.navigation:navigation-ui-ktx:$navigation_version",
            "androidx.navigation:navigation-common-ktx:$navigation_version",
            "androidx.room:room-runtime:$room_version",
            "androidx.room:room-ktx:$room_version",
            "org.koin:koin-android:$koin_version",
            "org.koin:koin-androidx-scope:$koin_version",
            "org.koin:koin-androidx-viewmodel:$koin_version",
            "me.dm7.barcodescanner:zxing:$barcode_scanner_version",
            "com.karumi:dexter:$dexter_permissions_version"
    kapt "androidx.room:room-compiler:$room_version"
    testImplementation "androidx.room:room-testing:$room_version"
    testImplementation "junit:junit:$junit_version"
    androidTestImplementation "androidx.test.ext:junit:$junit_ext_version",
            "androidx.test.espresso:espresso-core:$espresso_version"
}

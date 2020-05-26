object Libraries {

    private object Versions {
        const val appCompat = "1.1.0"
        const val constraintLayout = "1.1.3"
        const val ktx = "1.2.0"
        const val lifecycle = "2.2.0"
        const val navigation = "2.2.1"
        const val room = "2.2.5"
        const val kodein = "6.5.1"
        const val coroutinesAndroid = "1.3.2"
        const val barcodeScanner = "1.9.8"
        const val dexterPermissions = "6.0.2"
        const val retrofit = "2.7.2"
    }

    const val kotlinStdLib =
        "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Plugins.Versions.kotlin}"
    const val kotlinReflect = "org.jetbrains.kotlin:kotlin-reflect:${Plugins.Versions.kotlin}"
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    const val constraintLayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val coreKtx = "androidx.core:core-ktx:${Versions.ktx}"
    const val lifecycleExtensions = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle}"
    const val viewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    const val navigationRuntimeKtx =
        "androidx.navigation:navigation-runtime-ktx:${Versions.navigation}"
    const val navigationFragmentKtx =
        "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    const val navigationUiKtx = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
    const val navigationCommonKtx =
        "androidx.navigation:navigation-common-ktx:${Versions.navigation}"
    const val roomRuntime = "androidx.room:room-runtime:${Versions.room}"
    const val roomKtx = "androidx.room:room-ktx:${Versions.room}"
    const val roomCompiler = "android.arch.persistence.room:compiler:1.0.0"
    const val kodein = "org.kodein.di:kodein-di-generic-jvm:${Versions.kodein}"
    const val kodeinAndroidX = "org.kodein.di:kodein-di-framework-android-x:${Versions.kodein}"
    const val coroutinesAndroid =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutinesAndroid}"
    const val barcodeScanner = "me.dm7.barcodescanner:zxing:${Versions.barcodeScanner}"
    const val dexterPermissions = "com.karumi:dexter:${Versions.dexterPermissions}"
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofitMoshiConverter = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"
    const val materialSpinner = "com.jaredrummler:material-spinner:1.3.1"
    const val materialDatePicker = "com.wdullaer:materialdatetimepicker:4.2.3"
    const val anyChart = "com.github.AnyChart:AnyChart-Android:1.1.2"
}
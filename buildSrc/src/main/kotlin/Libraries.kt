package src.main.kotlin

object Libraries {

    private object Versions {
        const val appCompat = "1.1.0"
        const val constraintLayout = "1.1.3"
        const val ktx = "1.2.0"
        const val lifecycle = "2.2.0"
        const val navigation = "2.2.1"
        const val room = "2.2.5"
        const val koin = "2.0.1"
        const val barcodeScanner = "1.9.8"
        const val dexterPermissions = "6.0.2"
    }

    const val kotlinStdLib =
        "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${BuildPlugins.Versions.kotlin}"
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    const val constraintLayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val ktxCore = "androidx.core:core-ktx:${Versions.ktx}"
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
    const val koinAndroid = "org.koin:koin-android:${Versions.koin}"
    const val koinScope = "org.koin:koin-androidx-scope:${Versions.koin}"
    const val koinViewModel = "org.koin:koin-androidx-viewmodel:${Versions.koin}"
    const val barcodeScanner = "me.dm7.barcodescanner:zxing:${Versions.barcodeScanner}"
    const val dexterPermissions = "com.karumi:dexter:${Versions.dexterPermissions}"
}
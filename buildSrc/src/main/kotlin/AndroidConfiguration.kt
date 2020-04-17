object AndroidConfiguration {

    const val compileSdkVersion = 29
    const val minSdkVersion = 21
    const val targetSdkVersion = compileSdkVersion
    const val buildToolsVersion = "29.0.0"
    const val versionCode = 1
    const val versionName = "1.0"
    const val applicationId = "com.tp.fixedassetaccounting"
    const val testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

    sealed class BuildType(val name: String, val isMinifyEnabled: Boolean) {
        object Release : BuildType(RELEASE, false)
        object Debug : BuildType(DEBUG, false)
        private companion object {
            const val RELEASE = "release"
            const val DEBUG = "debug"
        }
    }
}
package src.main.kotlin

object TestLibraries {

    private object Versions {
        const val junit = "4.13"
        const val junitExt = "1.1.1"
        const val testRunner = "1.1.0"
        const val espresso = "3.2.0"
        const val room = "2.2.5"
    }

    const val junit = "junit:junit:${Versions.junit}"
    const val junitExt = "androidx.test.ext:junit:${Versions.junitExt}"
    const val testRunner = "androidx.test:runner:${Versions.testRunner}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    const val room = "androidx.room:room-testing:${Versions.room}"
}
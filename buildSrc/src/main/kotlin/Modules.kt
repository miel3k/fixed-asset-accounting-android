import kotlin.reflect.full.memberProperties

private const val FEATURE_PREFIX = ":feature_"

@Suppress("unused")
object Modules {

    const val APP = ":app"
    const val CORE = ":core"

    fun getAllModules() = Modules::class.memberProperties
        .filter { it.isConst }
        .map { it.getter.call().toString() }
        .toSet()

    fun getDynamicFeatureModules() = getAllModules()
        .filter { it.startsWith(FEATURE_PREFIX) }
        .toSet()
}
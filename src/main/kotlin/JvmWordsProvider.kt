import java.io.File
import java.net.URL

/**
 * JVM implementation of [WordsProvider].
 *
 * @property[resourceName] Name of the resource to read line-by-line from.
 */
class JvmWordsProvider(private val resourceName: String = "words.txt") : WordsProvider {

    private val file: File

    init {
        val resourceURL: URL = JvmWordsProvider::class.java.getResource(resourceName)
            ?: error("Could not get resource $resourceName")
        val resourcePath = resourceURL.path
        file = File(resourcePath)
    }

    override fun getWords(): MutableSet<String> = file.readLines().toMutableSet()
}

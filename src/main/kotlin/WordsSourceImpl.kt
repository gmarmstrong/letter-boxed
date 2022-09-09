import java.io.File
import java.net.URL

/**
 * JVM implementation of [WordsSource].
 *
 * @param[resourceName] Name of the resource to read line-by-line from.
 */
class WordsSourceImpl(private val resourceName: String = "words.txt") : WordsSource() {

    private val file: File

    init {
        val resourceURL: URL = WordsSourceImpl::class.java.getResource(resourceName)
            ?: throw IllegalStateException("Could not get resource '$resourceName'")
        val resourcePath = resourceURL.path
        file = File(resourcePath)
    }

    override fun getWords(): MutableSet<String> = file.readLines().toMutableSet()
}

/**
 * Constructor for a platform-specific (e.g., JavaScript or JVM) source for words.txt.
 */
abstract class WordsSource {
    abstract fun getWords(): MutableSet<String>
}

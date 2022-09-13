/**
 * Functional (SAM) interface for a WordsSource.
 */
fun interface WordsProvider {
    fun getWords(): MutableSet<String>
}

package letterboxed

/**
 * Functional (SAM) interface for a WordsSource.
 */
fun interface WordsProvider {
    /** Returns a mutable set of the strings contained in the source. */
    fun getWords(): MutableSet<String>
}

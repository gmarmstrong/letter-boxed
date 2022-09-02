import java.io.File

object WordsSourceImpl: WordsSource() {
    private const val WORDS_FILE_NAME = "words.txt"

    override fun getWords(): MutableSet<String> {
        val wordsResourceURL = WordsSourceImpl::class.java.getResource(WORDS_FILE_NAME)
            ?: throw IllegalStateException("Words file not found")
        val wordsFilePath = wordsResourceURL.file
        return File(wordsFilePath).readLines().toMutableSet()
    }
}
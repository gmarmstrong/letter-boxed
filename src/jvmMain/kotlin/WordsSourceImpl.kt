import java.io.File

object WordsSourceImpl: WordsSource() {
    override fun getWords(): MutableSet<String> {
        return File("words.txt").readLines().toMutableSet()
    }
}
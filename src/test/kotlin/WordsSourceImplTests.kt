import kotlin.test.Test
import kotlin.test.assertTrue

class WordsSourceImplTests {
    @Test
    fun returnWordsFromFile() {
        val wordsSource = WordsSourceImpl
        val words = wordsSource.getWords()
        assertTrue(words.size > 100)
        assertTrue(words.contains("hello"))
        assertTrue(words.contains("world"))
    }
}
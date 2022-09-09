import kotlin.test.Test
import kotlin.test.assertTrue

class WordsSourceImplTests {
    @Test
    fun testGetWords() {
        val wordsSource = WordsSourceImpl()
        val words = wordsSource.getWords()
        assertTrue(words.size > 100, "WordsSource should return more than 100 words")
        assertTrue(words.contains("hello"), """Strings should include "hello"""")
        assertTrue(words.contains("world"), """Strings should include "world"""")
    }
}
import java.io.File
import kotlin.test.Test
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

class WordsSourceImplTests {
    @Test
    fun `test getWords`() {
        val wordsSource = WordsSourceImpl()
        val words = wordsSource.getWords()
        assertTrue(words.size > 100, "WordsSource should return more than 100 words")
        assertTrue(words.contains("hello"), """Strings should include "hello"""")
        assertTrue(words.contains("world"), """Strings should include "world"""")
    }

    @Test
    fun `test missing file`() {
        check(!File("missing.txt").exists()) { "File missing.txt should not exist. This unit test will not be valid." }
        assertFailsWith<IllegalStateException> {
            WordsSourceImpl("missing.txt")
        }
    }
}

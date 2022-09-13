import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

/**
 * Checks whether certain strings are valid words _for specific [Puzzle]s_.
 *
 * @see isValidForPuzzle
 */
class AppliedValidityTests {
    private val abcSeed = "ABC,DEF,GHI,JKL"
    private val abcPuzzle = Puzzle(abcSeed)

    @Test
    fun `test applied validity correct`() = Assertions.assertTrue(isValidForPuzzle("KALE", abcPuzzle))

    @Test
    fun `test applied validity ignore case`() = Assertions.assertTrue(isValidForPuzzle("kaLE", abcPuzzle))

    @Test
    fun `test applied validity repeats edge`() = Assertions.assertFalse(isValidForPuzzle("BAKE", abcPuzzle))

    @Test
    fun `test applied validity unusable letter`() = Assertions.assertFalse(isValidForPuzzle("RECOGNIZE", abcPuzzle))

}
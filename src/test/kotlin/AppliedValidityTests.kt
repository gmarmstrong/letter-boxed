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
    fun testAppliedValidityCorrect() = Assertions.assertTrue(isValidForPuzzle("KALE", abcPuzzle))

    @Test
    fun testAppliedValidityIgnoreCase() = Assertions.assertTrue(isValidForPuzzle("kaLE", abcPuzzle))

    @Test
    fun testAppliedValidityRepeatsEdge() {
        Assertions.assertFalse(isValidForPuzzle("BAKE", abcPuzzle))
    }

    @Test
    fun testAppliedValidityUnusableLetter() {
        Assertions.assertFalse(isValidForPuzzle("RECOGNIZE", abcPuzzle))
    }

}
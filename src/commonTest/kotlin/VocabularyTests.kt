import kotlin.test.Test
import kotlin.test.assertEquals

class ValidSyntaxTests {
    @Test
    fun testValidityLength3() = assertEquals(true, isSyntacticallyValid("abc"))

    @Test
    fun testValidityGopher() = assertEquals(true, isSyntacticallyValid("gopher"))
}

class InvalidSyntaxTests {
    @Test
    fun testValidityEmptyString() = assertEquals(false, isSyntacticallyValid(""))

    @Test
    fun testValidityWhitespace() = assertEquals(false, isSyntacticallyValid(" "))

    @Test
    fun testValidityLength1() = assertEquals(false, isSyntacticallyValid("a"))

    @Test
    fun testValidityLength2() = assertEquals(false, isSyntacticallyValid("ab"))

    @Test
    fun testValidityAccents() = assertEquals(false, isSyntacticallyValid("español"))

    @Test
    fun testValidityRepeating() = assertEquals(false, isSyntacticallyValid("goose"))

    @Test
    fun testValidityRepeatingThrice() = assertEquals(false, isSyntacticallyValid("frillless"))

    @Test
    fun testValidityHyphen() = assertEquals(false, isSyntacticallyValid("corn-fed"))
}

class ValidForPuzzleTests {
    private val abcSeed = "ABC,DEF,GHI,JKL"
    private val abcPuzzle = Puzzle(abcSeed)

    @Test
    fun testAppliedValidityCorrect() = assertEquals(true, isValidForPuzzle("KALE", abcPuzzle))

    @Test
    fun testAppliedValidityIgnoreCase() = assertEquals(true, isValidForPuzzle("kaLE", abcPuzzle))
}

class InvalidForPuzzleTests {
    private val abcSeed = "ABC,DEF,GHI,JKL"
    private val abcPuzzle = Puzzle(abcSeed)

    @Test
    fun testAppliedValidityRepeatEdge() {
        assertEquals(false, isValidForPuzzle("BAKE", abcPuzzle))
    }

    @Test
    fun testAppliedValidityUnusableLetter() {
        val puzzle = Puzzle(abcSeed)
        assertEquals(false, isValidForPuzzle("RECOGNIZE", abcPuzzle))
    }
}
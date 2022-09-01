import kotlin.test.Test
import kotlin.test.assertEquals

class PuzzleTests {
    /** Test that Puzzles made from comma-separated seeds are equivalent to Puzzles made from set-based seeds. */
    @Test
    fun testEdgeDefinitionFlexible() {
        val seedString = "ABC,DEF,GHI,JKL"
        val seedSet = seedString.split(",").map { it.toSet() }.toSet()
        assertEquals(Puzzle(seedString), Puzzle(seedSet))
    }
}
import kotlin.test.Test
import kotlin.test.assertEquals

class PuzzleTests {
    /**
     * Test that comma-separated seeds are equivalent to string-list seeds.
     */
    @Test
    fun testEdgeDefinitionFlexible() {
        val seedA = "RME,WCL,KGT,IPA"
        val seedB = listOf("RME", "WCL", "KGT", "IPA")
        assertEquals(Puzzle(seedA), Puzzle(seedB))
    }
}
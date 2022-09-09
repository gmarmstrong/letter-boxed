import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals

internal class PuzzleTest {

    private lateinit var puzzle: Puzzle

    @BeforeEach
    fun setUp() {
        puzzle = Puzzle("ABC,DEF,GHI,JKL")
    }

    @Test
    fun testEdges() {
        val edges = puzzle.edges
        assertEquals(edges.size, 4, "Puzzle should have 4 edges")
        assertContains(edges, setOf('A', 'B', 'C'), "Puzzle should have edge ABC")
        assertContains(edges, setOf('D', 'E', 'F'), "Puzzle should have edge DEF")
        assertContains(edges, setOf('G', 'H', 'I'), "Puzzle should have edge GHI")
        assertContains(edges, setOf('J', 'K', 'L'), "Puzzle should have edge JKL")
    }

    @Test
    fun testLetters() {
    }

    @Test
    fun testCopy() {
    }

    @Test
    fun testEquals() {
    }

    @Test
    fun testHashCode() {
    }

    @Test
    fun testToString() {
    }
}
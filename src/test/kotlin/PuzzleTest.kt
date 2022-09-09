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
    fun `test edges`() {
        val edges = puzzle.edges
        assertEquals(edges.size, 4, "Puzzle should have 4 edges")
        assertContains(edges, setOf('A', 'B', 'C'), "Puzzle should have edge ABC")
        assertContains(edges, setOf('D', 'E', 'F'), "Puzzle should have edge DEF")
        assertContains(edges, setOf('G', 'H', 'I'), "Puzzle should have edge GHI")
        assertContains(edges, setOf('J', 'K', 'L'), "Puzzle should have edge JKL")
    }

    @Test
    fun `test letters`() {
        val expected = "ABCDEFGHIJKL".toSet()
        assertEquals(puzzle.letters, expected, "Puzzle should have letters A through L")
    }

    @Test
    fun `test equals`() {
        val puzzle2 = Puzzle("ABC,DEF,GHI,JKL")
        assertEquals(puzzle, puzzle2, "Puzzles should be equal")
    }

    @Test
    fun `test hashCode`() {
        val puzzle2 = Puzzle("ABC,DEF,GHI,JKL")
        assertEquals(puzzle.hashCode(), puzzle2.hashCode(), "Puzzles should have same hash code")
    }

    @Test
    fun `test toString`() {
        val expected = "ABC,DEF,GHI,JKL"
        assertEquals(expected, puzzle.toString())
    }
}
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertContains
import kotlin.test.assertEquals

internal class PuzzleTest {

    private val puzzle = Puzzle("ABC,DEF,GHI,JKL")

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

    @Test
    fun `test require edges per puzzle`() {
        require(EDGES_PER_PUZZLE == 4) { "This test assumes 4 edges per puzzle" }
        assertThrows<IllegalArgumentException> {
            Puzzle("ABC,DEF,GHI")
        }
    }

    @Test
    fun `test require letters per edge`() {
        require(LETTERS_PER_EDGE == 3) { "This test assumes 3 letters per edge" }
        assertThrows<IllegalArgumentException> {
            Puzzle("ABC,DE,FGH,IJK")
        }
    }

    @Test
    fun `test require letters per puzzle`() {
        require(EDGES_PER_PUZZLE * LETTERS_PER_EDGE == 12) { "This test assumes 12 letters per puzzle" }
        assertThrows<IllegalArgumentException> {
            Puzzle("ABC,ABD,EFG,HIJ")
        }
    }

    @Test
    fun `test require ASCII letters`() {
        assertThrows<IllegalArgumentException> {
            Puzzle(
                setOf(
                    setOf('A', 'B', 'C'),
                    setOf('D', '?', 'F'),
                    setOf('G', 'H', 'I'),
                    setOf('J', 'K', 'L')
                )
            )
        }
    }

    @Test
    fun `test require ASCII letters uppercase`() {
        assertThrows<IllegalArgumentException> {
            Puzzle(
                setOf(
                    setOf('A', 'B', 'C'),
                    setOf('D', 'e', 'F'),
                    setOf('G', 'H', 'I'),
                    setOf('J', 'K', 'L')
                )
            )
        }
    }
}

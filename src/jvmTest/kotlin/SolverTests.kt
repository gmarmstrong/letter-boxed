import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class SolverTests {
    private val puzzle = Puzzle("QRE,LOU,IAY,CNG")
    private val abcPuzzle = Puzzle("ABC,DEF,GHI,JKL")
    private val qreSolution = listOf("uniquely", "Yogacara")
    private val qreBadSolution = listOf("car", "rogue")
    private val solver = Solver(WordsSourceImpl)

    @Test
    fun testSolvesQre() = with(solver) {
        assertTrue(qreSolution solves puzzle, "QRE puzzle should be solved by $qreSolution")
    }

    @Test
    fun testNotSolvesQre() = with(solver) {
        assertFalse(qreBadSolution solves puzzle, "QRE puzzle should not be solved by $qreBadSolution")
    }

    @Test
    fun testNotSolvesAbc() = with(solver) {
        assertFalse(qreSolution solves abcPuzzle, "ABC puzzle should not be solved by $qreSolution")
    }

    @Test
    fun testQreSolutions() {
        val qreSolutions = solver.solve(puzzle)
        assertTrue(qreSolutions.isNotEmpty(), "QRE puzzle should have solutions")
        assertTrue(qreSolutions.contains(qreSolution), "QRE puzzle should have solution $qreSolution")
        with(solver) {
            qreSolutions.forEach {
                assertTrue(it solves puzzle, "QRE puzzle should be solved by $it")
            }
        }
    }

    @Test
    fun testSolverSolveImpossible() {
        val abcSolutions = solver.solve(abcPuzzle)
        assertTrue(abcSolutions.isEmpty(), "ABC puzzle should have no solutions")
    }
}
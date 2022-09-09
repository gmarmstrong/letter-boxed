import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class SolverTests {
    private val seed = "QRE,LOU,IAY,CNG"
    private val puzzle = Puzzle(seed)
    private val abcSeed = "ABC,DEF,GHI,JKL"
    private val abcPuzzle = Puzzle(abcSeed)

    private lateinit var solver: Solver

    @BeforeEach
    fun setup() {
        solver = Solver(WordsSourceImpl)
    }

    @Test
    fun testIsSolution() {
        val qreSolution = listOf("uniquely", "Yogacara")
        val qreBadSolution = listOf("car", "rogue")
        with(solver) {
            assertTrue(qreSolution solves puzzle, "QRE puzzle should be solved by $qreSolution")
            assertFalse(qreBadSolution solves puzzle, "QRE puzzle should not be solved by $qreBadSolution")
            assertFalse(qreSolution solves abcPuzzle, "ABC puzzle should have no solutions")
        }
    }

    @Test
    fun testSolverSolve() {
        val solutions = solver.solve(puzzle)
        assertTrue(solutions.isNotEmpty(), "No solutions found")
        assertTrue(solutions.contains(listOf("uniquely", "Yogacara")))
        with(solver) {
            solutions.forEach {
                assertTrue(it solves puzzle, "Puzzle should be solved by $it")
            }
        }
    }

    @Test
    fun testSolverSolveImpossible() {
        val solutions = solver.solve(abcPuzzle)
        assertTrue(solutions.isEmpty(), "The ABC puzzle should have no solutions")
    }
}
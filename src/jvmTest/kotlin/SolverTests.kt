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
        assertTrue(solver.isSolution(listOf("uniquely", "Yogacara"), puzzle))
        assertFalse(solver.isSolution(listOf("car", "rogue"), puzzle))
    }

    @Test
    fun testSolverSolve() {
        val solutions = solver.solve(puzzle)
        assertTrue(solutions.isNotEmpty(), "No solutions found")
        assertTrue(solutions.contains(listOf("uniquely", "Yogacara")))
        for (solution in solutions) {
            assertTrue(solver.isSolution(solution, puzzle), "Not all of these solutions are valid")
        }
    }

    @Test
    fun testSolverSolveImpossible() {
        val solutions = solver.solve(abcPuzzle)
        assertTrue(solutions.isEmpty(), "The ABC puzzle should have no solutions")
    }
}
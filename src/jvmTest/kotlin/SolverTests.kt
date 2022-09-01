import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertFalse
import kotlin.test.assertTrue

// This arguably belongs in commonTest, but it currently depends on the JVM for WordsSourceImpl.
class SolverTests {
    private val seed = "QRE,LOU,IAY,CNG"
    private val puzzle = Puzzle(seed)
    private val abcSeed = "ABC,DEF,GHI,JKL"
    private val abcPuzzle = Puzzle(abcSeed)

    @Test
    fun testIsSolution() {
        val solver = Solver(WordsSourceImpl)
        assertTrue(solver.isSolution(listOf("uniquely", "Yogacara"), puzzle))
        assertFalse(solver.isSolution(listOf("car", "rogue"), puzzle))
    }

    @Test
    fun testSolverSolve() {
        val solver = Solver(WordsSourceImpl)
        val solutions = solver.solve(puzzle)
        assertTrue(solutions.isNotEmpty(), "No solutions found")
        assertContains(solutions, listOf("uniquely", "Yogacara"))
        for (solution in solutions) {
            assertTrue(solver.isSolution(solution, puzzle), "Not all of these solutions are valid")
        }
        println(solutions.joinToString("\n"))
    }

    @Test
    fun testSolverSolveImpossible() {
        val solver = Solver(WordsSourceImpl)
        val solutions = solver.solve(abcPuzzle)
        assertTrue(solutions.isEmpty(), "The ABC puzzle should have no solutions")
    }
}
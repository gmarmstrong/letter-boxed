
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

class SolverTests {
    private val puzzle = Puzzle("QRE,LOU,IAY,CNG")
    private val abcPuzzle = Puzzle("ABC,DEF,GHI,JKL")
    private val qreSolution = listOf("uniquely", "Yogacara")
    private val qreBadSolution = listOf("car", "rogue")
    private val wordSource = WordsSourceImpl()
    private val solver = Solver(wordSource)

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

    @Test
    fun testSolverOneStep() {
        // Shuffle any 12-letter heterogram: https://en.wikipedia.org/wiki/Heterogram_(literature)#12_letters
        val puzzle = Puzzle("ADR,MEO,BXU,ITS")
        val oneSolver = Solver(wordSource, 1)
        val solutions = oneSolver.solve(puzzle)
        solutions.forEach(::println)
        assertTrue(solutions.contains(listOf("ambidextrous")), "ADR,MEO,BXU,ITS puzzle should have solution: ambidextrous")
    }

    @Test
    @Disabled // FIXME fails (slowly) with OutOfMemoryError
    fun testSolverThreeSteps() {
        val puzzle = Puzzle("QRE,LOU,IAY,CNG")
        val threeSolver = Solver(wordSource, 3)
        val solutions = threeSolver.solve(puzzle)
        assertTrue(solutions.isNotEmpty())
    }
}
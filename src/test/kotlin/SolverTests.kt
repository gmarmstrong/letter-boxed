
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.util.function.BooleanSupplier
import kotlin.test.assertContains

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
        assertContains(qreSolutions, qreSolution, "QRE puzzle should have solution $qreSolution")
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
        assertContains(solutions, listOf("ambidextrous"), "ADR,MEO,BXU,ITS puzzle should have solution: ambidextrous")
    }

    /**
     * Tests that the solver can do 1-solves for solutions which start
     * and end with the same letter. Specifically, this tests a possible
     * edge case of [alternatesEdges] implementations.
     */
    @Test
    fun testSolverOneStepEqualEnds() {
        val customWordSource = object : WordsSource() {
            override fun getWords(): MutableSet<String> {
                return mutableSetOf("abcdefghijkla")
            }
        }
        val puzzle = Puzzle("AEI,BFJ,CGK,DHL")
        val oneSolver = Solver(customWordSource, 1)
        val solutions = oneSolver.solve(puzzle)
        solutions.map { it.toString() }.forEach(::println)
        assertContains(solutions, listOf("abcdefghijkla"), "AEI,BFJ,CGK,DHL puzzle should have solution: abcdefghijkla")
    }

    @Test
    fun testSolverThreeSteps() {
        val puzzle = Puzzle("QRE,LOU,IAY,CNG")
        val threeSolver = Solver(wordSource, 3)
        val solutions = threeSolver.solve(puzzle)
        solutions.forEach(::println)
        assertTrue(solutions.isNotEmpty())
    }
}

private fun <T> Sequence<T>.isEmpty(): BooleanSupplier {
    return BooleanSupplier { !iterator().hasNext() }
}

private fun <T> Sequence<T>.isNotEmpty(): BooleanSupplier {
    return BooleanSupplier { iterator().hasNext() }
}

/**
 * Logic for solving the puzzles.
 */
class Solver(wordsSource: WordsSource) {
    var solutionSteps = 2

    val words: MutableSet<String>

    init {
        words = wordsSource.getWords()
        pruneGenerally(words)
    }

    fun solve(puzzle: Puzzle): List<String> {
        pruneForPuzzle(words, puzzle)
        throw NotImplementedError()
    }

    fun isValidSolution(solution: List<String>, puzzle: Puzzle): Boolean {
        // FIXME Assumes all the words are uppercase.
        // Checks that only letters from the puzzle are used, and that all the letters are used at least once.
        if (!solution.usesAlphabet(puzzle.letters) || !solution.fulfillsAlphabet(puzzle.letters)) {
            return false
        }
        // Checks that the last letter of each word is the same as the first letter of the next word.
        for (pair in solution.zipWithNext()) {
            if (pair.first.last() != pair.second.first()) {
                return false
            }
        }
        return true
    }
}

/** Checks that each character in a string is on a different edge than the previous character. */
fun String.alternatesEdges(edges: Set<Set<Char>>): Boolean {
    for ((first, second) in this.zipWithNext()) {
        val firstEdge = edges.find { it.contains(first) }
        val secondEdge = edges.find { it.contains(second) }
        if (firstEdge == secondEdge) {
            return false
        }
    }
    return true
}

/** Checks that a string only uses characters from a set of characters. */
fun String.usesAlphabet(chars: Set<Char>): Boolean = this.all { it in chars }

/** Checks that a list of strings only uses characters from a set of characters. */
private fun List<String>.usesAlphabet(chars: Set<Char>): Boolean = this.all { it.usesAlphabet(chars) }

/** Checks that a list of strings uses every character from a set of characters at least once. */
private fun List<String>.fulfillsAlphabet(chars: Set<Char>): Boolean = chars.all { it in this.joinToString("") }

const val MIN_WORD_LENGTH = 3

/**
 * Logic for solving the puzzles.
 */
class Solver(wordsProvider: WordsProvider, private val solutionSteps: Int = 2) {

    val words: MutableSet<String> = wordsProvider.getWords()

    init {
        // Filters a list of words to remove words that could never be legal in any solution.
        words.retainAll { isSyntacticallyValid(it) }
    }

    /**
     * Returns a sequence of all possible puzzle solutions
     * which use exactly [solutionSteps] words.
     */
    fun solve(puzzle: Puzzle): Sequence<List<String>> {
        words.retainAll { isValidForPuzzle(it, puzzle) }
        return permute(words.toList(), length = solutionSteps).filter { it solves puzzle }
    }

    /**
     * Determines if a list of words are a solution to the puzzle.
     * Does not check
     */
    infix fun List<String>.solves(puzzle: Puzzle): Boolean {
        return size == solutionSteps &&
            usesAlphabet(puzzle.letters) &&
            fulfillsAlphabet(puzzle.letters) &&
            lettersConnected()
    }
}

/**
 * Checks that the last letter of each word is the same as the first letter of the next word.
 */
fun List<String>.lettersConnected(): Boolean {
    for ((a, b) in this.zipWithNext()) {
        if (a.last().uppercaseChar() != b.first().uppercaseChar()) {
            return false
        }
    }
    return true
}

/** Checks that each character in a string is on a different edge than the previous character. */
fun String.alternatesEdges(edges: Set<Set<Char>>): Boolean {
    for ((first, second) in this.zipWithNext()) {
        val firstEdge = edges.find { it.contains(first.uppercaseChar()) }
        val secondEdge = edges.find { it.contains(second.uppercaseChar()) }
        if (firstEdge == secondEdge) {
            return false
        }
    }
    return true
}

/** Generates permutations of the given [length] for a [list] of items. */
fun <T> permute(list: List<T>, length: Int): Sequence<List<T>> =
    // Generate a sequence of the yielded lists
    sequence {
        if (length == 1) { // base case
            for (item in list) {
                yield(listOf(item))
            }
        } else { // recursive case
            list.forEach { item ->
                permute(list, length - 1).forEach { perm ->
                    yield(listOf(item) + perm)
                }
            }
        }
    }

/** Checks that a string only uses characters from a set of characters. */
fun String.usesAlphabet(chars: Set<Char>): Boolean = this.all { it.uppercaseChar() in chars }

/** Checks that a list of strings only uses characters from a set of characters. */
private fun List<String>.usesAlphabet(chars: Set<Char>): Boolean = this.all { it.usesAlphabet(chars) }

/** Checks that a list of strings uses every character from a set of characters at least once. */
private fun List<String>.fulfillsAlphabet(chars: Set<Char>): Boolean =
    chars.all { it.uppercaseChar() in this.joinToString("").uppercase() }

/**
 * Checks if a word is syntactically valid. That is, that the word is at least three characters long,
 * contains only ASCII letters (case-insensitive), and contains no "double letters" (e.g., "egg" fails
 * because it contains "gg").
 */
fun isSyntacticallyValid(word: String): Boolean =
    word.length >= MIN_WORD_LENGTH &&
        word.all { it in 'A'..'z' } &&
        word.lowercase().zipWithNext().none { it.first == it.second }

/**
 * Checks if a word is valid for the given targetPuzzle. Assumes isSyntacticallyValid(word) is true.
 */
fun isValidForPuzzle(word: String, targetPuzzle: Puzzle): Boolean =
    word.usesAlphabet(targetPuzzle.letters) &&
        word.alternatesEdges(targetPuzzle.edges)

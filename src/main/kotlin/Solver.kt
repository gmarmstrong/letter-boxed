/**
 * Logic for solving the puzzles.
 */
class Solver(wordsSource: WordsSource, private val solutionSteps: Int = 2) {

    val words: MutableSet<String> = wordsSource.getWords()

    init {
        // Filters a list of words to remove words that could never be legal in any solution.
        words.retainAll { isSyntacticallyValid(it) }
    }

    fun solve(puzzle: Puzzle): Sequence<List<String>> {
        words.retainAll { isValidForPuzzle(it, puzzle) }
        return permute(words.toList(), length = solutionSteps).filter { it solves puzzle }
    }

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
fun <T> permute(list: List<T>, length: Int): Sequence<List<T>> {
    return if (length == 1) {
        list.asSequence().map { listOf(it) }
    } else {
        sequence {
            for (i in list.indices) {
                val head = list[i]
                val tail = list.subList(i + 1, list.size)
                for (perm in permute(tail, length - 1)) {
                    yield(listOf(head) + perm)
                }
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
 * contains only ASCII letters (case-insensitive), and contains no consecutive duplicate letters (e.g., "egg" fails
 * because it contains "gg").
 */
fun isSyntacticallyValid(word: String): Boolean {
    // Check word length
    if (word.length < 3) {
        return false
    }
    // Check that all characters are ASCII letters
    if (!word.all { it in 'A'..'z' }) {
        return false
    }
    // Check that the word contains no consecutive duplicate letters
    return word.lowercase().zipWithNext().none { it.first == it.second }
}

/**
 * Checks if a word is valid for the given targetPuzzle. Assumes isSyntacticallyValid(word) is true.
 */
fun isValidForPuzzle(word: String, targetPuzzle: Puzzle): Boolean {
    if (!word.usesAlphabet(targetPuzzle.letters)) {
        return false
    }
    if (!word.alternatesEdges(targetPuzzle.edges)) {
        return false
    }
    return true
}

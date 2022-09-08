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
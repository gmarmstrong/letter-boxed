/**
 * Filters a list of words to remove words that could never be legal in any solution.
 */
fun pruneGenerally(words: List<String>): List<String> {
    return words.filter { isSyntacticallyValid(it) }
}

/**
 * Filters a list of words to remove words that could never be valid for the given targetPuzzle.
 */
fun pruneForPuzzle(words: List<String>, targetPuzzle: Puzzle): List<String> {
    return words.filter { isSyntacticallyValid(it) }
}

/**
 * Checks if a word is syntactically valid.
 */
private fun isSyntacticallyValid(word: String): Boolean {
    // Check that the word is at least 3 characters long.
    if (word.length < 3) {
        return false
    }
    // Check that all characters are letters.
    if (!word.all { it.isLetter() }) {
        return false
    }
    // Check that the word contains no repeated consecutive letters (e.g., "egg" fails because it contains "gg").
    return word.lowercase().zipWithNext().none { it.first == it.second }
}

/**
 * Checks if a word is valid for the given targetPuzzle. Assumes isSyntacticallyValid(word) is true.
 */
private fun isValidForPuzzle(word: String, targetPuzzle: Puzzle): Boolean {
    // TODO Check that the word contains only letters that are in the targetPuzzle.
    // TODO Check that each letter alternates between edges of the puzzle.
    throw NotImplementedError()
}
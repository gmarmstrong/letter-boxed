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
        throw NotImplementedError()
    }
}
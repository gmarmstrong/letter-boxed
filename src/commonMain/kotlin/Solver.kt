class Solver(wordsSource: WordsSource) {
    var solutionSteps = 2

    val words: MutableSet<String> = wordsSource.getWords()

    fun solve(puzzle: Puzzle): List<String> {
        throw NotImplementedError()
    }

    fun isValidSolution(solution: List<String>, puzzle: Puzzle): Boolean {
        throw NotImplementedError()
    }
}
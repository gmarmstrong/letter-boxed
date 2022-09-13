/** Entry point for the application. */
fun main(args: Array<String>) {
    val solver = Solver(JvmWordsProvider())
    val seed = args[0]
    val puzzle = Puzzle(seed)
    val solutions = solver.solve(puzzle)
    solutions.forEach(::println)
}

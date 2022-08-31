data class Puzzle(
    val edges: List<String>,
) {
    /**
     * Constructs a puzzle from a comma-separated list of edges.
     */
    constructor(input: String) : this(input.split(","))
}

@Throws(NotImplementedError::class)
fun solve(
    puzzle: Puzzle,
    maxWords: Int,
): Sequence<Sequence<String>> {
    throw NotImplementedError()
}
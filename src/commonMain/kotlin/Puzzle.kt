class Puzzle {
    // edges is a list of 4 strings, each containing 3 unique characters
    var edges: Set<Set<Char>> = mutableSetOf()

    constructor(edges: Set<Set<Char>>) {
        // Establish requirements for puzzle dimensions
        require(edges.size == 4) { "Puzzle must have exactly 4 unique edges" }
        require(edges.all { it.size == 3 }) { "Each edge must be exactly 3 unique characters" }
        // Establish requirements for characters (one of the 26 uppercase Latin letters)
        require(edges.all { edge -> edge.all { it in 'A'..'Z' } }) { "Each edge must contain only uppercase Latin letters" }
        // Set puzzle edges
        this.edges = edges
    }

    /** Constructs a puzzle from a string of comma-separated of edges. */
    constructor(input: String) : this(parseEdges(input))
}

/**
 * Parses a string of comma-separated edges into a set of sets of characters.
 * For example, "ABC,DEF,GHI,JKL" becomes { {"A", "B", "C"}, {"D", "E", "F"}, {"G", "H", "I"}, {"J", "K", "L"} }.
 */
private fun parseEdges(input: String): Set<Set<Char>> {
    return input.split(",")
        .map { it.toSet() }
        .toSet()
}

fun solve(
    puzzle: Puzzle,
    maxWords: Int,
): Sequence<Sequence<String>> {
    throw NotImplementedError()
}
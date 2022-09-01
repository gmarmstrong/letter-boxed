class Puzzle {
    /** edges should be a set of 4 sets of 3 uppercase Latin letters. */
    val edges: Set<Set<Char>>

    /**
     * Constructs a puzzle from the given edges.
     *
     * @throws IllegalArgumentException if the edges do not form a valid puzzle.
     */
    @Throws(IllegalArgumentException::class)
    constructor(edges: Set<Set<Char>>) {
        // Establish requirements for puzzle dimensions
        require(edges.size == 4) { "Puzzle must have exactly 4 unique edges" }
        require(edges.all { it.size == 3 }) { "Each edge must be exactly 3 unique characters" }
        // Establish requirements for characters (one of the 26 uppercase Latin letters)
        require(edges.all { edge -> edge.all { it in 'A'..'Z' } }) { "Each edge must contain only uppercase Latin letters" }
        // Set puzzle edges
        this.edges = edges
    }

    /**
     * Constructs a puzzle from a string of comma-separated of edges.
     *
     * @throws IllegalArgumentException if the string is not a valid puzzle.
     */
    constructor(input: String) : this(parseEdges(input))

    // Define Puzzle equality by equality of edges.
    override fun equals(other: Any?): Boolean {
        return other is Puzzle && other.edges == edges
    }

    // Define Puzzle hashCode by hashCode of edges.
    override fun hashCode(): Int = edges.hashCode()

    // Define Puzzle toString by concatenating edges.
    override fun toString(): String = edges.joinToString(separator = ",") { edge -> edge.joinToString(separator = "") }


}

/**
 * Parses a string of comma-separated edges into a set of sets of characters.
 * Also converts all letters to uppercase (Turkish-I problem avoided by the requirement of uppercase ASCII letters).
 *
 * For example, "ABC,DEF,GHI,JKL" becomes { {"A", "B", "C"}, {"D", "E", "F"}, {"G", "H", "I"}, {"J", "K", "L"} }.
 */
private fun parseEdges(input: String): Set<Set<Char>> {
    return input
        .uppercase()
        .split(",")
        .map { it.toSet() }
        .toSet()
}

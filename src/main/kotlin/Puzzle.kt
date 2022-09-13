/**
 * Represents a Letter Boxed puzzle configuration.
 */
data class Puzzle(val edges: Set<Set<Char>>) {

    val letters: Set<Char>
        get() = edges.flatten().toSet()

    /**
     * Constructs a puzzle from a set of 4 edges (sets of 3 uppercase ASCII characters).
     */
    init {
        require(edges.size == 4) { "Puzzle must have exactly 4 unique edges" }
        require(edges.all { it.size == 3 }) { "Each edge must be exactly 3 unique characters" }
        require(edges.all { edge -> edge.all { it in 'A'..'Z' } }) { "Each edge must contain only uppercase ASCII letters" }
        require(edges.flatten().toSet().size == 12) { "Puzzle must consist of exactly 12 unique characters" }
    }

    /**
     * Constructs a puzzle from a string of comma-separated of edges. Letters are converted to uppercase.
     *
     * @throws IllegalArgumentException if the string is not a valid puzzle.
     */
    constructor(input: String) : this(parseEdges(input))

    // Define Puzzle equality by equality of edges.
    override fun equals(other: Any?): Boolean = other is Puzzle && other.edges == edges

    // Define Puzzle hashCode by hashCode of edges.
    override fun hashCode(): Int = edges.hashCode()

    // Define Puzzle toString by concatenating edges.
    override fun toString(): String = edges.joinToString(separator = ",") { edge -> edge.joinToString(separator = "") }
}

/**
 * Parses a string of comma-separated edges into a set of sets of characters.
 * Also converts all letters to uppercase (Turkish-I problem avoided by the requirement of uppercase ASCII letters).
 *
 * For example, "ABC,DEF,GHI,JKL" becomes { {'A', 'B', 'C'}, {'D', 'E', 'F'}, {'G', 'H', 'I'}, {'J', 'K', 'L'} }.
 */
private fun parseEdges(input: String): Set<Set<Char>> {
    return input
        .uppercase()
        .split(",")
        .map { it.toSet() }
        .toSet()
}

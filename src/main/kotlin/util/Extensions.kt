package util

/**
 * Checks whether this list will "zigzag" over [edges].
 * That is, checks that each element of this list fits
 * onto a different edge than the previous element.
 */
fun <T> List<T>.zigzagsOver(edges: Set<Set<T>>): Boolean =
    zipWithNext().none { (a, b) ->
        edges.any { a in it && b in it }
    }

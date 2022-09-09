
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

/**
 * Checks whether certain strings are syntactically valid words.
 *
 * @see isSyntacticallyValid
 */
class StringSyntaxTests {
    private val goodStrings = setOf(
        "abc",
        "gopher",
    )

    private val badStrings = setOf(
        "",
        " ",
        "a",
        "ab",
        "español",
        "goose",
        "frillless",
        "corn-fed",
    )

    @Test
    fun `test valid strings`() {
        goodStrings.forEach {
            assertTrue(isSyntacticallyValid(it), "'$it' should be valid")
        }
    }

    @Test
    fun `test invalid strings`() {
        badStrings.forEach {
            assertFalse(isSyntacticallyValid(it), "'$it' should not be valid")
        }
    }
}


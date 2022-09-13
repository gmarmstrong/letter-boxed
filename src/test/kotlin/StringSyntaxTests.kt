import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

/**
 * Checks whether certain strings are syntactically valid words.
 *
 * @see isSyntacticallyValid
 */
class StringSyntaxTests {

    @Nested
    inner class ValidStrings {
        @Test
        fun `test valid string (abc)`() = assertValid("abc")

        @Test
        fun `test valid string (gopher)`() = assertValid("gopher")

        @Test
        fun `test valid string (GOPher)`() = assertValid("GOPher")
    }

    @Nested
    inner class InvalidStrings {

        @Nested
        inner class WhitespaceProblems {
            @Test
            fun `test invalid string ()`() = assertInvalid("")

            @Test
            fun `test invalid string (   )`() = assertInvalid("   ")

            @Test
            fun `test valid string ( abc )`() = assertInvalid(" abc ")

            @Test
            fun `test invalid string (abc + tab character)`() = assertInvalid("abc\t")

            @Test
            fun `test invalid string (abc + newline character)`() = assertInvalid("abc\n")

            @Test
            fun `test invalid string (abc + carriage return character)`() = assertInvalid("abc\r")

            @Test
            fun `test invalid string (ab + null character)`() = assertInvalid("ab\u0000")

            @Test
            fun `test invalid string (abc + backspace character)`() = assertInvalid("abc\b")

            @Test
            fun `test invalid string (a b) (space-separated)`() = assertInvalid("a b")

            @Test
            fun `test invalid string (a	b) (tab-separated)`() = assertInvalid("a\tb")

            @Test
            fun `test invalid string (abc def)`() = assertInvalid("abc def")
        }

        @Nested
        inner class LengthProblems {
            @Test
            fun `test invalid string (a)`() = assertInvalid("a")

            @Test
            fun `test invalid string (ab)`() = assertInvalid("ab")
        }

        @Nested
        inner class CharacterProblems {
            @Test
            fun `test invalid string (abc123)`() = assertInvalid("abc123")

            @Test
            fun `test invalid string (espanol) (with n-tilde accent)`() = assertInvalid("español")

            @Test
            fun `test invalid string (corn-fed)`() = assertInvalid("corn-fed")
        }

        @Nested
        inner class RepetitionProblems {

            @Test
            fun `test invalid string (goose)`() = assertInvalid("goose")

            @Test
            fun `test invalid string (frillless)`() = assertInvalid("frillless")

            @Test
            fun `test invalid string (abB)`() = assertInvalid("abB")

            @Test
            fun `test invalid string (abcC)`() = assertInvalid("abcC")
        }
    }
}

@Suppress("NOTHING_TO_INLINE") // for debugger, although the inline could be unnecessary
private inline fun assertValid(s: String) = assertTrue(isSyntacticallyValid(s))

@Suppress("NOTHING_TO_INLINE") // for debugger, although the inline could be unnecessary
private inline fun assertInvalid(s: String) = assertFalse(isSyntacticallyValid(s))

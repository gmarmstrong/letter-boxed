import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.ByteArrayOutputStream
import java.io.PrintStream

/**
 * Functional tests for the [main] entry point.
 */
class MainTest {
    /* Puzzles and their expected outputs. */
    private val expectations = mapOf(
        arrayOf("RME,WCL,KGT,IPA") to "[wigwam, marketplace]",
        arrayOf("LWC,GTK,ERM,PIA") to "[wigwam, marketplace]",
        arrayOf("BGY,ULN,MIS,TOK") to "[yolks, stumbling]",
        arrayOf("ABC,DEF,GHI,JKL") to "",
        arrayOf("RAP,TZV,INE,OLH") to """
            [helper, revitalization]
            [pother, revitalization]
            [philter, revitalization]
            [telpher, revitalization]
            [virilize, Ethiopian]
            [heliozoan, nonrepetitive]
            [perihelion, novelization]
        """.trimIndent(),
    )

    private lateinit var out: ByteArrayOutputStream

    @BeforeEach
    fun setUp() {
        out = ByteArrayOutputStream()
        System.setOut(PrintStream(out))
    }

    @Test
    fun `test main output`() {
        for ((input, expected) in expectations) {
            main(input)
            val output = out.toString().trim()
            assertEquals(expected, output, "Unexpected output for input $input (got $output, expected $expected)")
            out.reset()
        }
    }

    @Test
    fun `test main duplicate edge`() {
        assertThrows(
            IllegalArgumentException::class.java,
            { main(arrayOf("ABC,ABC,ABC,ABC")) },
            "Expected error for duplicate edges, got ${out.toString().trim()} instead"
        )
    }

    @Test
    fun `test main too many edges`() {
        assertThrows(
            IllegalArgumentException::class.java,
            { main(arrayOf("ABC,DEF,GHI,JKL,MNO")) },
            "Expected error for too many edges, got ${out.toString().trim()} instead"
        )
    }

    @Test
    fun `test main too few edges`() {
        assertThrows(
            IllegalArgumentException::class.java,
            { main(arrayOf("ABC,DEF,GHI")) },
            "Expected error for too few edges, got ${out.toString().trim()} instead"
        )
    }
}
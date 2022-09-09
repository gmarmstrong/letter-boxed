import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.ByteArrayOutputStream
import java.io.PrintStream

/**
 * Functional tests for the [main] entry point.
 */
class MainTest {
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
        """.trimIndent()
    )

    @Test
    fun mainOutputTest() {
        val out = ByteArrayOutputStream()
        System.setOut(PrintStream(out))
        for ((input, expected) in expectations) {
            main(input)
            assertEquals(expected, out.toString().trim())
            out.reset()
        }
    }
}
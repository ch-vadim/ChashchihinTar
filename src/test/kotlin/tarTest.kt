import org.junit.Test
import java.io.File
import kotlin.test.assertEquals
import org.junit.Assert.assertThrows
import java.lang.IllegalArgumentException

class Test {
    private fun assertFileContent(name: String, expectedContent: String) {
        val file = File(name)
        val content = file.readLines().joinToString("\n")
        assertEquals(expectedContent, content)
    }

    @Test
    fun tarConnect() {
        main(arrayOf("input/rt.txt", "input/pr.txt", "-out", "input/resf.txt"))
        assertFileContent(
            "input/resf.txt",
            """☻ File was create with TarUtility
2 3
☻ Name of file input/rt.txt
qwerty
tttttt
☻ Name of file input/pr.txt
truipfjfvredv
wgrsver
vfdfsvger"""
        )
        File("input/resf.txt").delete()
    }

    @Test
    fun tarSplit() {
        main(arrayOf("-u", "input/res2.txt"))
        assertFileContent(
            "ffff.txt",
            """qwerty
tttttt"""
        )
        assertFileContent(
            "ttttt.txt",
            """truipfjfvredv
wgrsver
vfdfsvger"""
        )
        assertFileContent(
            "spasibo.txt",
            """kak
zhe
bilo
slozhno
no
interesno"""
        )
        File("ffff.txt").delete()
        File("ttttt.txt").delete()
        File("spasibo.txt").delete()

    }
    @Test
    fun testExceptions() {
        assertThrows(IllegalArgumentException::class.java) { main(arrayOf()) }
        assertThrows(IllegalArgumentException::class.java) { main(arrayOf("-u", "input/rt", "input/pr", "-out", "input/resf" )) }
        assertThrows(IllegalArgumentException::class.java) { main(arrayOf("input/rt.txt", "-out", "input/resf.txt" )) }

    }
}
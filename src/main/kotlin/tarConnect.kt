import java.io.File
import java.lang.IllegalArgumentException

/*Format of file:
* ☻ File was create with TarUtility
* 12 23 43
* ☻ Name of file $nameFile
* [text]
* ☻ Name of file $nameFile
* [text]
* ...
*/

fun tarConnect(input: MutableList<String>, output: String) {
    val magicConstants = MagicConstants()
    if (input.size < 2) throw IllegalArgumentException("Count of file must be 2 and more")
    var sizeFile:Int
    val listOfSize = mutableListOf<Int>()
    val tempFile = createTempFile("resultFile", ".txt", null)
    tempFile.bufferedWriter().use { writer ->
        for (nameFile in input) {
            File(nameFile).bufferedReader().use { reader ->
                sizeFile = 0
                writer.write("${magicConstants.splitConstant}$nameFile\n")
                reader.forEachLine {
                    sizeFile++
                    writer.write("$it\n")
                }
                listOfSize.add(sizeFile)
            }
        }
    }
    File(output).bufferedWriter().use { writer ->
        writer.write("${magicConstants.prefixConstant}\n")
        writer.write(listOfSize.joinToString(separator = " "))
        writer.newLine()
        tempFile.bufferedReader().use { reader ->
            reader.forEachLine { writer.write("$it\n") }
        }
    }
    tempFile.delete()
}
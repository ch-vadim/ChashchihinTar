import java.io.File
import java.io.IOException
import java.lang.IllegalArgumentException

/*Format of file:
* 12 23 43
* ☻ File was create with TarUtility
*☻ Name of file $nameFile
* [text]
* ☻ Name of file $nameFile
* [text]
* ...
*/

fun tarConnect(input: MutableList<String>, output: String) {
    if (input.size < 2) throw IllegalArgumentException("Count of file must be 2 and more")
    var sizeFile:Int
    val listOfSize = mutableListOf<Int>()
    val writer = File("resultTextForTar.txt").bufferedWriter()
    for (nameFile in input) {
        val file = try {
            File(nameFile).bufferedReader()
        } catch (e: IOException) {
            throw IllegalArgumentException("File $nameFile could not be opened")
        }
        sizeFile = 0
        writer.write("☻ Name of file $nameFile\n")
        file.forEachLine {
            sizeFile++
            writer.write("$it\n")
        }
        listOfSize.add(sizeFile)
    }
    writer.close()
    val result = File(output).bufferedWriter()
    result.write("☻ File was create with TarUtility\n")
    result.write(listOfSize.joinToString(separator = " "))
    result.newLine()
    File("resultTextForTar.txt").forEachLine {
        result.write(it)
        result.newLine()
    }
    result.close()
    File("resultTextForTar.txt").delete()
}
import java.io.File
import java.io.IOException
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
    val magicConstant = "☻ Name of file " //changed here?, change in tarSplit too
    if (input.size < 2) throw IllegalArgumentException("Count of file must be 2 and more")
    var sizeFile:Int
    val listOfSize = mutableListOf<Int>()
    File("resultTextForTar.txt").bufferedWriter().use { writer ->
        for (nameFile in input) {
            val file = try {
                File(nameFile).bufferedReader()
            } catch (e: IOException) {
                throw IllegalArgumentException("File $nameFile could not be opened")
            }
            sizeFile = 0
            writer.write("$magicConstant$nameFile\n")
            file.forEachLine {
                sizeFile++
                writer.write("$it\n")
            }
            listOfSize.add(sizeFile)
        }

    }
    File(output).bufferedWriter().use { writer ->
        writer.write("☻ File was create with TarUtility\n")
        writer.write(listOfSize.joinToString(separator = " "))
        writer.newLine()
        File("resultTextForTar.txt").forEachLine {
            writer.write(it)
            writer.newLine()
        }
    }
    File("resultTextForTar.txt").delete()
}
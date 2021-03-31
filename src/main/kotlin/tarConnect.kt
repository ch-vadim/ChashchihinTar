import java.io.File
import java.io.IOException
import java.lang.IllegalArgumentException

fun tarConnect(input: MutableList<String>, output: String) {
    if (input.size < 3) throw IllegalArgumentException("Count of file mast be 2 and more")
    val files = input.subList(1, input.size)
    val writer = File(output).bufferedWriter()
    writer.write("☻ File was create with TarUtility")
    writer.newLine()
    for (nameFile in files) {
        val file = try {
            File(nameFile).readLines()
        } catch (e: IOException) {
            throw IllegalArgumentException("File $nameFile could not be opened")
        }
        writer.write("☻ Name of file $nameFile")
        writer.newLine()
        for(line in file) {
            writer.write(line)
            writer.newLine()
        }
    }
    writer.close()
}
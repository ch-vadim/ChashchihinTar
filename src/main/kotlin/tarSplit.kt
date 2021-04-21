import java.io.File
import java.io.IOException
import java.lang.IllegalArgumentException

fun tarSplit(nameFile: String) {
    val file = try{
        File(nameFile).bufferedReader()
    } catch (e: IOException) {
        throw IllegalArgumentException("File $nameFile could not be opened")
    }
    if (file.readLine() != "☻ File was create with TarUtility") throw IllegalArgumentException("This file did't create with TarUtility")
    var str = file.readLine()
    val listOfSize = if (str.contains(Regex("""[^\d ]"""))) {
        throw IllegalArgumentException()
    } else str.split(" ")
    for (e in listOfSize) {
        val name = file.readLine() ?: throw IllegalArgumentException()
        if (!name.startsWith("☻ Name of file ")) throw IllegalArgumentException()
        val result = File(name.substring(15)).bufferedWriter()
        repeat(e.toInt()) {
            str = file.readLine() ?: throw IllegalArgumentException()
            result.write(str)
            result.newLine()
        }
        result.close()
    }
    if (file.readLine() != null) throw IllegalArgumentException()
    file.close()

}
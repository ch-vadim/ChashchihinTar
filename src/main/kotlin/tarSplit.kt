import java.io.File
import java.io.IOException
import java.lang.IllegalArgumentException

fun tarSplit(nameFile: String) {
    val file = try{
        File(nameFile).bufferedReader()
    } catch (e: IOException) {
        throw IllegalArgumentException("File $nameFile could not be opened")
    }
    val magicConstant = "☻ Name of file " //changed here?, change in tarConnect too
    if (file.readLine() != "☻ File was create with TarUtility")
        throw IllegalArgumentException("This file did't create with TarUtility")
    var str = file.readLine()
    val listOfSize = if (str.contains(Regex("""[^\d ]"""))) {
        throw IllegalArgumentException("Error when running the utility")
    } else str.split(" ")
    for (e in listOfSize) {
        val name = file.readLine() ?: throw IllegalArgumentException("Error when running the utility")
        if (!name.startsWith(magicConstant))
            throw IllegalArgumentException("Error when running the utility")
        File(name.substring(magicConstant.length)).bufferedWriter().use { writer ->
            repeat(e.toInt()) {
                str = file.readLine() ?: throw IllegalArgumentException("Error when running the utility")
                writer.write(str)
                writer.newLine()
            }
        }
    }
    if (file.readLine() != null) throw IllegalArgumentException("Error when running the utility")
    file.close()

}
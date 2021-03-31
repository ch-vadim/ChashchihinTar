import java.io.File
import java.io.IOException
import java.lang.IllegalArgumentException

fun tarSplit(nameFile: String) {
    val file = try{
        File(nameFile).readLines()
    } catch (e: IOException) {
        throw IllegalArgumentException("File $nameFile could not be opened")
    }
    if (file[0] != "☻ File was create with TarUtility") throw IllegalArgumentException("This file did't create with TarUtility")
    var i = 2
    var name = file[1].substring(15)
    var result = File(name).bufferedWriter()
    while (i < file.size) {
        if (file[i].startsWith("☻ Name of file ")) {
            result.close()
            name = file[i].substring(15)
            result = File(name).bufferedWriter()
            i++
            continue
        }
        result.write(file[i])
        result.newLine()
        i++
    }
    result.close()
}
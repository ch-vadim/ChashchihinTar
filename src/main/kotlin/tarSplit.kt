import java.io.File
import java.io.IOException
import java.lang.IllegalArgumentException

fun tarSplit(nameFile: String) {
    val magicConstants = MagicConstants()
        File(nameFile).bufferedReader().use { reader ->
            if (reader.readLine() != magicConstants.prefixConstant)
                throw IllegalArgumentException("This file did't create with TarUtility")
            var str = reader.readLine()
            val listOfSize = if (str.contains(Regex("""[^\d ]"""))) {
                throw IllegalArgumentException("Error when running the utility")
            } else str.split(" ")
            for (e in listOfSize) {
                val name = reader.readLine() ?: throw IllegalArgumentException("Error when running the utility")
                if (!name.startsWith(magicConstants.splitConstant))
                    throw IllegalArgumentException("Error when running the utility")
                File(name.substring(magicConstants.splitConstant.length)).bufferedWriter().use { writer ->
                    repeat(e.toInt()) {
                        str = reader.readLine() ?: throw IllegalArgumentException("Error when running the utility")
                        writer.write("$str\n")
                    }
                }
            }
            if (reader.readLine() != null) throw IllegalArgumentException("Error when running the utility")
        }

}
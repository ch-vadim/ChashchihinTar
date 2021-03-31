import java.lang.IllegalArgumentException
import org.kohsuke.args4j.*

 class CommandLineValues {
    @Option(name = "-u", forbids = ["-out"])
    var u: String? = null

    @Option(name = "-out", forbids = ["-u"])
    var out: String? = null

    @Argument
    var input = mutableListOf<String>()


    fun launch(args: Array<String>) {
        val parser = CmdLineParser(this)
        try {
            parser.parseArgument(args.toMutableList())
        } catch (e: CmdLineException) {
            throw IllegalArgumentException("Input: tar filename.txt filename.txt ... -out filename.txt OR tar -u filename.txt")
        }
        if ((u ==null && out == null) || input.size < 1 || input[0] != "tar") {
            throw IllegalArgumentException(
                "Input: tar filename.txt filename.txt ... -out filename.txt OR tar -u filename.txt")
        }
        if (u != null) { tarSplit(u!!) }
        else if (out!= null) tarConnect(input, out!!)
    }

}
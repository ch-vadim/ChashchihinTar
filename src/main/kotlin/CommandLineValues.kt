import java.lang.IllegalArgumentException
import org.kohsuke.args4j.*

 class CommandLineValues {
    @Option(name = "-u", forbids = ["-out"])
    private var u: String? = null

    @Option(name = "-out", forbids = ["-u"])
    var out: String? = null

    @Argument
    var input = mutableListOf<String>()


    fun launch(args: Array<String>) {
        val parser = CmdLineParser(this)
        try {
            parser.parseArgument(args.toMutableList())
        } catch (e: CmdLineException) {
            throw IllegalArgumentException("Input: filename.txt filename.txt ... -out filename.txt OR tar -u filename.txt")
        }
        if (u ==null && (out == null || input.isEmpty())) {
            throw IllegalArgumentException(
                "Input: filename.txt filename.txt ... -out filename.txt OR tar -u filename.txt")
        }
        if (u != null) { tarSplit(u!!) }
        else if (out!= null) tarConnect(input, out!!)
    }

}
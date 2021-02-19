package tools

import java.io.BufferedReader
import java.io.InputStreamReader
import java.nio.charset.StandardCharsets
import java.util.stream.Collectors

class FileReader {

    companion object{
        fun readToBufferedRead(path: String): BufferedReader
        {
            val inputStream = {}.javaClass.classLoader.getResourceAsStream(path)
            val inputStreamReader = InputStreamReader(inputStream!!, StandardCharsets.UTF_8)

            return BufferedReader(inputStreamReader)
        }

        fun readToString(path: String): String = readToBufferedRead(path).lines().collect(Collectors.joining("\n"))
    }

}
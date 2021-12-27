package se.nyquist

import java.io.File

class DataReader(fileName: String) {
    val completedInput = mutableListOf<AutoCompleterStatus>()
    private val file = File(fileName)
    private val lines = file.readLines()
    var score : Int = 0

    init {
        val parser = Parser()
        val autoCompleter = AutoCompleter()
        for (i in lines.indices) {
            val status = parser.parse(lines[i])
            score += status.score
            val autoCompleterStatus = autoCompleter.complete(lines[i], status.symbols)
            if (autoCompleterStatus.score > 0) {
                completedInput.add(autoCompleterStatus)
            }
        }
    }
}



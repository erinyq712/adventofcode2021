package se.nyquist

class AutoCompleter {
    private val scores = mapOf(
        Pair(')', 1),
        Pair(']', 2),
        Pair('}', 3),
        Pair('>', 4),
    )

    fun complete(input: String, status: ParserStatus) : AutoCompleterStatus {
        if (status.symbols.isEmpty() || status.errorPosition != -1) {
            return AutoCompleterStatus("",0)
        }
        return complete(input, status.symbols)
    }

    fun complete(input: String, symbols: MutableList<Char>) : AutoCompleterStatus {
        val tail : MutableList<Char> = mutableListOf()
        var score = 0L
        for (i in symbols.lastIndex downTo 0) {
            val current = symbols[i]
            if (isBegin(current)) {
                val endCharacter = getExpectedEnd(current)
                tail.add(endCharacter)
                score *= 5
                score += scores.getOrDefault(endCharacter,0)
            }
        }
        val completed = input + tail.joinToString("")
        return AutoCompleterStatus(completed, score)
    }
}
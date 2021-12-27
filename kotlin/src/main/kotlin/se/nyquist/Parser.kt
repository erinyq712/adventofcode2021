package se.nyquist

val beginCharacters = "{[<("
val endCharacters = "}]>)"

fun getExpected(status: ParserStatus): Char {
    val current = status.symbols[status.symbols.lastIndex]
    return endCharacters[beginCharacters.indexOf(current)]
}

fun getExpectedEnd(current: Char): Char {
    return endCharacters[beginCharacters.indexOf(current)]
}

fun isMatching(c: Char, status: ParserStatus): Boolean {
    if (status.symbols.isEmpty()) {
        return false
    }
    val current = status.symbols[status.symbols.lastIndex]
    return endCharacters.indexOf(c) == beginCharacters.indexOf(current)
}

fun isBegin(c: Char): Boolean {
    return beginCharacters.contains(c)
}

fun isEnd(c: Char): Boolean {
    return endCharacters.contains(c)
}

class Parser {
    private val scores = mapOf(
        Pair(')', 3),
        Pair(']', 57),
        Pair('}', 1197),
        Pair('>', 25137),
    )

    fun parse(input: String) : ParserStatus {
        val status = ParserStatus(0)
        processNext(input[0], input.substring(1), status)
        return status
    }

    private fun processNext(c: Char, substring: String, status: ParserStatus) {
        if (isBegin(c)) {
            status.symbols.add(c)
        } else if (isEnd(c)) {
            if (isMatching(c, status)) {
                status.symbols.removeLast()
            } else {
                status.errorPosition = status.position
                val expected = getExpected(status)
                status.message = "Expecting ${expected}, but found ${c}"
                status.score = scores.getOrDefault(c,0)
                return
            }
        } else {
            status.errorPosition = status.position
            status.message = "Unexpected character: ${c}"
            return
        }
        if (substring.isNotEmpty()) {
            status.position = status.position + 1
            processNext(substring[0], substring.substring(1), status)
        }
    }
}
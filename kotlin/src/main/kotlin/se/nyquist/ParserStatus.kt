package se.nyquist

data class ParserStatus(var position: Int, val symbols : MutableList<Char> = mutableListOf<Char>()) {
    var errorPosition : Int = -1
    var message: String = ""
    var score: Int = 0
    val isIncomplete: Boolean get() = symbols.isNotEmpty()
}

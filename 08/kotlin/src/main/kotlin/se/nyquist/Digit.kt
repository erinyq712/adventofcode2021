package se.nyquist

//     abcdefg
// 0 = 1110111
// 1 = 0010010
// 2 = 1011101
// 3 = 1011011
// 4 = 0111010
// 5 = 1101011
// 6 = 1101111
// 7 = 1010010
// 8 = 1111111
// 9 = 1111011

enum class Digit(val number: Int, val representation : String, val signals : Int) {
    ZERO(0, "abcefg", 5),
    ONE(1, "cf", 2),
    TWO(2, "acdeg", 5),
    THREE(3, "acdfg", 5),
    FOUR(4, "bcdf", 4),
    FIVE(5, "abdfg",5),
    SIX(6, "abdefg", 6),
    SEVEN(7, "acf", 3),
    EIGHT(8, "abcdefg", 7),
    NINE(9, "abcdfg", 6),
    UNKNOWN(-1, "", 0);

    override fun toString(): String {
        return "Digit(number=$number)"
    }

    fun toInt() : Int {
        return number
    }
}

fun matchDigitsOnPattern(str : String) : Array<Digit> {
    return when (str.length) {
        2 -> arrayOf(Digit.ONE)
        3 -> arrayOf(Digit.SEVEN)
        4 -> arrayOf(Digit.FOUR)
        5 -> arrayOf(Digit.ZERO, Digit.TWO, Digit.THREE, Digit.FIVE)
        6 -> arrayOf(Digit.SIX, Digit.NINE)
        7 -> arrayOf(Digit.EIGHT)
        else -> arrayOf()
    }
}

fun getDigit(input : String) : Digit {
    if (input.equals(Digit.ZERO.representation))
        return Digit.ZERO
    else if (input.equals(Digit.ONE.representation))
        return Digit.ONE
    else if (input.equals(Digit.TWO.representation))
        return Digit.TWO
    else if (input.equals(Digit.THREE.representation))
        return Digit.THREE
    else if (input.equals(Digit.FOUR.representation))
        return Digit.FOUR
    else if (input.equals(Digit.FIVE.representation))
        return Digit.FIVE
    else if (input.equals(Digit.SIX.representation))
        return Digit.SIX
    else if (input.equals(Digit.SEVEN.representation))
        return Digit.SEVEN
    else if (input.equals(Digit.EIGHT.representation))
        return Digit.EIGHT
    else if (input.equals(Digit.NINE.representation))
        return Digit.NINE
    else
        return Digit.UNKNOWN
}

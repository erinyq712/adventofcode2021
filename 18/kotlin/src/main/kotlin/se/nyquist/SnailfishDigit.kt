package se.nyquist

data class SnailfishDigit(var digit: Int) : SnailfishNumber {
    override fun reduce(): SnailfishNumber {
        return this
    }

    override fun findReducable(depth: Int): SnailfishNumberPair? {
        return null
    }

    override fun getDigits(level: Int): List<SnailfishDigit> {
        return listOf(this)
    }

    operator fun plusAssign(number: SnailfishDigit) {
        digit += number.digit
    }

    override fun replace(pair: SnailfishNumberPair, snailfishDigit: SnailfishDigit) {
        // Empty
    }

    override fun replace(snailfishDigit: SnailfishDigit, pair: SnailfishNumberPair) {
        // Empty
    }

    override fun isDigit(): Boolean {
        return true
    }

    override fun explode(): SnailfishNumber {
        return this
    }

    override fun split(): SnailfishNumber {
        return SnailfishNumberPair(
            SnailfishDigit(this.digit / 2),
            SnailfishDigit((this.digit + 1) / 2)
        )
    }

    override fun toString(): String {
        return digit.toString()
    }

    override fun magnitude(): Long {
        return digit.toLong()
    }

}
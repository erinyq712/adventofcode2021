package se.nyquist

class SingleNumber(private val number: Int) : GenerateNumber {
    private var current = 0

    override fun hasNext(): Boolean {
        return current != number
    }

    override fun next(): Int {
        current = number
        return current
    }
}
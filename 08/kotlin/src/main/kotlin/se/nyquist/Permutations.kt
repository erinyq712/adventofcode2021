package se.nyquist

import java.math.BigInteger

class Permutations(private val start : Int) : GenerateNumber {
    private var current = 0
    private var next : Int
    private var base = BigInteger.valueOf(10L)
    private var digits : Int

    init {
        this.next = start
        this.digits = countdigits(start)
    }

    // Function to count the total number of digits
    // in a number.
    fun countdigits(n: Int): Int {
        var current = n
        var count = 0
        while (current > 0) {
            count++
            current = current / 10
        }
        return count
    }

    override fun hasNext() : Boolean {
        return current == 0 || next != start
    }

    override fun next() : Int {
        current = next
        val rem = BigInteger.valueOf(next % 10L)
        val div = BigInteger.valueOf(next / 10L)
        next = base.pow(digits - 1).multiply(rem).plus(div).toInt()
        return current
    }
}
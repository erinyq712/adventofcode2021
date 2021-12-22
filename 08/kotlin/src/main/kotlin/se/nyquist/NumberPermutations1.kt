package se.nyquist

import java.lang.RuntimeException
import java.math.BigInteger

class NumberPermutations1(private val digits : IntArray) : GenerateNumber {
    val size = digits.size
    var current = 0
    val base = BigInteger.valueOf(10L)
    var tailPermutations : GenerateNumber
    var head : BigInteger

    init {
        val map = digits.groupBy{ it }.filter{ it.value.size > 1 }
        if (map.isNotEmpty()) throw RuntimeException("Values must be distinct")
        if (size <= 1) throw RuntimeException("Must provide at least two values")
        if (size == 2) {
            tailPermutations = SingleNumber(digits[1])
        } else {
            tailPermutations = NumberPermutations1(digits.filter { it != digits[current] }.toIntArray())
        }
        head = base.pow(size - 1).multiply(digits[current].toBigInteger())
    }

    override fun hasNext(): Boolean {
        return current < size
    }

    override fun next(): Int {
        val tail : Int
        if (tailPermutations.hasNext()) {
            tail = tailPermutations.next()
        } else {
            current++
            if (size == 2) {
                head = base.pow(size - 1).multiply(digits[1].toBigInteger())
                tailPermutations = SingleNumber(digits[0])
                current++
            } else if (current < size) {
                head = base.pow(size - 1).multiply(digits[current].toBigInteger())
                tailPermutations = NumberPermutations1(digits.filter { it != digits[current] }.toIntArray())
            } else {
                throw RuntimeException("Unexpected state")
            }
            tail = tailPermutations.next()
        }
        if (current == size-1 && ! tailPermutations.hasNext()) {
            current++
        }
        return head.add(tail.toBigInteger()).toInt()
    }
}
package se.nyquist

import java.lang.RuntimeException
import java.math.BigInteger

class NumberPermutations(private val digits : IntArray) : GenerateNumber {
    private val size = digits.size
    private var state: State

    private val base = BigInteger.valueOf(10L)

    data class State(val current : Int, val head : BigInteger, var tailPermutations : GenerateNumber?) {
        var flag : Boolean = false

        fun hasNext() : Boolean {
            return !flag
        }

        fun hasTailNext() : Boolean {
            return tailPermutations != null && tailPermutations!!.hasNext()
        }

        fun next() : Int {
            val result =
                if (hasTailNext()) {
                    head.add(tailPermutations!!.next().toBigInteger()).toInt()
                } else {
                    head.toInt()
                }
            flag = ! hasTailNext()
            return result
        }
    }

    private constructor(digits : IntArray, state: State) : this(digits) {
        this.state = state
    }

    init {
        this.state =
            if (size > 1)
                State(0, base.pow(size - 1).multiply(digits[0].toBigInteger()), NumberPermutations(digits.filter { it != digits[0]}.toIntArray()))
            else
                State(0, digits[0].toBigInteger(), null)

        val map = digits.groupBy{ it }.filter{ it.value.size > 1 }
        if (map.isNotEmpty()) throw RuntimeException("Values must be distinct")
    }

    override fun hasNext(): Boolean {
        return state.current < size
    }

    override fun next(): Int {
        // Must make sure that state.hasNext() is always true HERE if hasNext() has returned true
        val result = state.next()
        if (! state.hasNext()) {
            val current = state.current + 1
            if (current < digits.size) {
                this.state = State(
                    current,
                    base.pow(size - 1).multiply(digits[current].toBigInteger()),
                    NumberPermutations(digits.filter { it != digits[current] }.toIntArray())
                )
            } else {
                this.state = State(
                    current,
                    BigInteger.ZERO,
                    null
                )
            }
        }
        return result
    }
}
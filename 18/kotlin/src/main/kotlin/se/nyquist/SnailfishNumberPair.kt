package se.nyquist

import java.util.*

class SnailfishNumberPair(var left : SnailfishNumber, var right: SnailfishNumber)  : SnailfishNumber {
    override fun reduce(): SnailfishNumber {
        var done = false
        while (! done) {
            val pair = findReducable()
            if (pair  != null) {
                this.explode(pair)
            } else {
                val splitDigit = getDigits().find { it.digit > 9 }
                if (splitDigit != null) {
                    split(splitDigit)
                } else {
                    done = true
                }
            }
        }
        return this
    }

    override fun split() : SnailfishNumber {
        val splitDigit = getDigits().find { it.digit > 9 }
        if (splitDigit != null) {
            split(splitDigit)
        }
        return this
    }

    override fun magnitude(): Long {
        return left.magnitude()*3 + right.magnitude()*2
    }

    private fun split(splitDigit: SnailfishDigit) : SnailfishNumber {
        replace(
            splitDigit,
            SnailfishNumberPair(
                SnailfishDigit(splitDigit.digit / 2),
                SnailfishDigit((splitDigit.digit + 1) / 2)
            )
        )
        return this
    }

    override fun findReducable(depth: Int): SnailfishNumberPair? {
        if (depth <= 0 && left.isDigit() && right.isDigit()) {
            return this
        } else {
            val leftReducable = left.findReducable(depth - 1)
            if (leftReducable != null) {
                return leftReducable
            }
            val rightReducable = right.findReducable(depth - 1)
            if (rightReducable != null) {
                return rightReducable
            }
        }
        return null
    }

    override fun getDigits(level: Int): List<SnailfishDigit> {
        val result = mutableListOf<SnailfishDigit>()
        result.addAll(left.getDigits(level+1))
        result.addAll(right.getDigits(level+1))
        return result
    }

    override fun replace(pair: SnailfishNumberPair, snailfishDigit: SnailfishDigit) {
        if (left == pair) {
            left = snailfishDigit
        } else if (right == pair) {
            right = snailfishDigit
        } else {
            left.replace(pair, snailfishDigit)
            right.replace(pair, snailfishDigit)
        }
    }


    override fun replace(snailfishDigit: SnailfishDigit, pair: SnailfishNumberPair) {
        if (left === snailfishDigit) {
            left = pair
        } else if (right === snailfishDigit) {
            right = pair
        } else {
            left.replace(snailfishDigit, pair)
            right.replace(snailfishDigit, pair)
        }
    }

    override fun toString(): String {
        return "[${left.toString()},${right.toString()}]"
    }

    override fun isDigit(): Boolean {
        return false
    }

    override fun explode(): SnailfishNumber {
        val pair = findReducable()
        if (pair != null) {
            explode(pair)
        }
        return this
    }

    private fun explode(pair: SnailfishNumberPair): SnailfishNumberPair {
        val digits = getDigits()
        val firstDigit = pair.left as SnailfishDigit
        val firstIndex = digits.indices.first { digits[it] === firstDigit }
        if (firstIndex > 0) {
            digits[firstIndex - 1] += digits[firstIndex]
        }
        val secondDigit = pair.right as SnailfishDigit
        val secondIndex = digits.indices.first { digits[it] === secondDigit }
        if (secondIndex < digits.lastIndex) {
            digits[secondIndex + 1] += digits[secondIndex]
        }
        replace(pair, SnailfishDigit(0))
        return this
    }
}
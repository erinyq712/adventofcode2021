package se.nyquist

import java.lang.NumberFormatException

interface SnailfishNumber {

    fun reduce() : SnailfishNumber

    fun findReducable(): SnailfishNumberPair? {
        return findReducable(4)
    }
    fun findReducable(depth: Int): SnailfishNumberPair?

    fun getDigits(): List<SnailfishDigit> {
        return getDigits(0)
    }

    fun getDigits(level : Int): List<SnailfishDigit>

    fun replace(pair: SnailfishNumberPair, snailfishDigit: SnailfishDigit)

    fun replace(snailfishDigit: SnailfishDigit, pair: SnailfishNumberPair)

    fun isDigit() : Boolean
    fun explode(): SnailfishNumber
    fun split(): SnailfishNumber
    fun magnitude(): Long
}

fun parse(input: String) : SnailfishNumber {
    if (input.first() == '[' && input.last() == ']') {
        var position = 1
        val subitem1 = parseNext(input, position)
        position = subitem1.first
        while (input[position] != ',') {
            position++
        }
        val number2 = parseNext(input, position+1)
        return SnailfishNumberPair(subitem1.second, number2.second)
    } else if (input.first().isDigit() && input.last().isDigit()) {
        return if (input.contains(","))
            SnailfishNumberPair(SnailfishDigit(input.first().digitToInt()), SnailfishDigit(input.last().digitToInt()))
        else SnailfishDigit(input.toInt())
    } else if (input.first() == '[' && input.last().isDigit()) {
        return SnailfishNumberPair(parse(input.substring(1, input.lastIndexOf(']'))), SnailfishDigit(input.last().digitToInt()))
    } else if (input.last() == ']' && input.first().isDigit()) {
        return SnailfishNumberPair(SnailfishDigit(input.first().digitToInt()), parse(input.substring(input.lastIndexOf('[') + 1, input.lastIndex)))
    } else {
        throw NumberFormatException(input)
    }
}

private fun parseNext(input: String, startPosition: Int): Pair<Int,SnailfishNumber> {
    var position = startPosition
    if (input[position] == '[') {
        var beginCount = 1
        var endCount = 0
        do {
            position++
            if (input[position] == '[') {
                beginCount++
            } else if (input[position] == ']') {
                endCount++
            }
        } while (beginCount > endCount)
        return Pair(position + 1, parse(input.substring(startPosition, position + 1)))
    } else if (input[position].isDigit()) {
        return Pair(position + 1, SnailfishDigit(input[position].digitToInt()))
    }
    throw NumberFormatException(input)
}

operator fun SnailfishNumber.plus(number: SnailfishNumber) : SnailfishNumber {
    return SnailfishNumberPair(this, number).reduce()
}
package se.nyquist

import java.math.BigInteger

fun <T> getPermutationsWithDistinctValues(original: List<T>): Set<List<T>> {
    if (original.isEmpty())
        return emptySet()
    val permutationInstructions = original.toSet()
        .map { it to original.count { x -> x == it } }
        .fold(listOf(setOf<Pair<T, Int>>())) { acc, (value, valueCount) ->
            mutableListOf<Set<Pair<T, Int>>>().apply {
                for (set in acc) for (retainIndex in 0 until valueCount) add(set + (value to retainIndex))
            }
        }
    return mutableSetOf<List<T>>().also { outSet ->
        for (instructionSet in permutationInstructions) {
            outSet += original.toMutableList().apply {
                for ((value, retainIndex) in instructionSet) {
                    repeat(retainIndex) { removeAt(indexOfFirst { it == value }) }
                    repeat(count { it == value } - 1) { removeAt(indexOfLast { it == value }) }
                }
            }
        }
    }
}

fun <T> allPermutations(digits : List<T>) : List<List<T>> {
    val size = digits.size
    var current = 0
    val values = mutableListOf<List<T>>()
    while (current < size) {
        val head = digits[current]
        if (size> 1) {
            val subList = digits.filter { it != head }.toList()
            val tails = allPermutations(subList)
            for (tail in tails) {
                val newList = mutableListOf<T>(head)
                newList.addAll(tail)
                values.add(newList)
            }
        } else {
            values.add(mutableListOf<T>(head))
        }
        current++
    }
    return values
}

fun allNumberPermutations(digits : IntArray) : IntArray {
    val size = digits.size
    var current = 0
    val base = BigInteger.valueOf(10L)
    val values = mutableListOf<Int>()
    while (current < digits.size) {
        val head = base.pow(size-1).multiply(digits[current].toBigInteger())
        if (size> 1) {
            val tails = allNumberPermutations(digits.filter { it != digits[current] }.toIntArray())
            for (tail in tails) {
                values.add(head.add(tail.toBigInteger()).toInt())
            }
        } else {
            values.add(head.toInt())
        }
        current++
    }
    return values.toIntArray()
}
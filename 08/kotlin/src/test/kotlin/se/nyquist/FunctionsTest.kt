package se.nyquist

import org.junit.jupiter.api.Test

class FunctionsTest {


    @Test
    fun allNumberPermutations() {
        val digits = intArrayOf(1,2,3)
        val allValues = allNumberPermutations(digits)
        allValues.forEach {  println(it) }
    }

    @Test
    fun allStringPermutations() {
        val elements = arrayListOf("a","b","c")
        val allValues = allPermutations(elements)
        allValues.forEach {  println(it.joinToString("")) }
    }
}

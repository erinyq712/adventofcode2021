package se.nyquist

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class DecoderTest {
    private val decoder = Decoder()

    @Test
    fun decode() {
        val input = arrayListOf<String>("acedgfb","cdfbe","gcdfa","fbcad","dab","cefabd","cdfgeb","eafb","cagedb","ab")
        val output = arrayListOf<String>("cdfeb", "fcadb", "cdfeb", "cdbaf")
        assertEquals(5353, decoder.decode(input, output))
    }

}
package se.nyquist

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class PermutationsTest {

    private val sut = Permutations(71)

    @Test
    operator fun next() {
        assertTrue(sut.hasNext())
        var next = sut.next()
        assertEquals(71, next)
        assertTrue(sut.hasNext())
        next = sut.next()
        assertEquals(17, next)
        assertFalse(sut.hasNext())
    }
}
package se.nyquist

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class SingleNumberTest {

    private val sut = SingleNumber(7)

    @Test
    fun next() {
        assertTrue(sut.hasNext())
        val next = sut.next()
        assertFalse(sut.hasNext())
        assertEquals(7, next)
    }
}
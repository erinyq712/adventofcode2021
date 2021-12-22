package se.nyquist

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class NumberPermutationsTest {

    @Test
    fun next() {
        val digits = intArrayOf(1,2,3)
        val sut = NumberPermutations(digits)
        assertTrue(sut.hasNext())
        var next = sut.next()
        assertEquals(123, next)
        assertTrue(sut.hasNext())
        next = sut.next()
        assertEquals(132, next)
        next = sut.next()
        assertEquals(213, next)
        assertTrue(sut.hasNext())
        next = sut.next()
        assertEquals(231, next)
        while(sut.hasNext()) {
            next = sut.next()
            assertTrue(next>0)
        }
    }

    @Test
    fun next0() {
        val digits = intArrayOf(0,1,2,3)
        val sut = NumberPermutations(digits)
        assertTrue(sut.hasNext())
        var next = sut.next()
        assertEquals(123, next)
        assertTrue(sut.hasNext())
        next = sut.next()
        assertEquals(132, next)
        next = sut.next()
        assertEquals(213, next)
        assertTrue(sut.hasNext())
        next = sut.next()
        assertEquals(231, next)
        while(sut.hasNext()) {
            next = sut.next()
            assertTrue(next>0)
        }
    }


    @Test
    fun next4() {
        val digits = intArrayOf(1,2,3,4)
        val sut = NumberPermutations(digits)
        var counter = 0
        assertTrue(sut.hasNext())
        sut.next()
        counter++
        while(sut.hasNext()) {
            val next = sut.next()
            assertTrue(next>0)
            counter++
        }
        assertEquals(24, counter)
    }

    @Test
    fun next5() {
        val digits = intArrayOf(1,2,3,4,5)
        val sut = NumberPermutations(digits)
        var counter = 0
        assertTrue(sut.hasNext())
        sut.next()
        counter++
        while(sut.hasNext()) {
            val next = sut.next()
            assertTrue(next>0)
            counter++
        }
        assertEquals(120, counter)
    }
}
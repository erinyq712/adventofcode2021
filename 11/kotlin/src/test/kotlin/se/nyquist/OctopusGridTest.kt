package se.nyquist

import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Test

class OctopusGridTest {
    private val input = arrayOf(
        intArrayOf(1,1,1,1,1),
        intArrayOf(1,9,9,9,1),
        intArrayOf(1,9,1,9,1),
        intArrayOf(1,9,9,9,1),
        intArrayOf(1,1,1,1,1)
    )

    private val step1 = arrayOf(
        intArrayOf(3,4,5,4,3),
        intArrayOf(4,0,0,0,4),
        intArrayOf(5,0,0,0,5),
        intArrayOf(4,0,0,0,4),
        intArrayOf(3,4,5,4,3),
    )

    private val step2 = arrayOf(
        intArrayOf(4,5,6,5,4),
        intArrayOf(5,1,1,1,5),
        intArrayOf(6,1,1,1,6),
        intArrayOf(5,1,1,1,5),
        intArrayOf(4,5,6,5,4),
    )

    private val octopusGrid = OctopusGrid(input)

    @Test
    fun next() {
        val next1 = octopusGrid.next()
        assertArrayEquals(step1, next1)
        val next2 = octopusGrid.next()
        assertArrayEquals(step2, next2)
    }
}
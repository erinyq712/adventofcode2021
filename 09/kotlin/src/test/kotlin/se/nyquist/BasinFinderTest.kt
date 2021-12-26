package se.nyquist

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class BasinFinderTest {
    private val input = create()
    private val lowPointDetector = LowPointDetector()
    private val basinFinder = BasinFinder(lowPointDetector.detect(input).toTypedArray(), input)

    @Test
    fun find() {
        val result = basinFinder.find()
        assertEquals(4, result.size)
        assertEquals(3, result[0].size)
        assertEquals(9, result[1].size)
        assertEquals(14, result[2].size)
        assertEquals(9, result[3].size)
    }

    private fun create(): Array<Array<Int>> {
        val input = listOf("2199943210","3987894921","9856789892","8767896789", "9899965678")
        return input.map { splitString(it).toTypedArray() }.toTypedArray()
    }

}

package se.nyquist

import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.test.assertEquals

class LowPointDetectorTest {
    private val lowPointDetector = LowPointDetector()

    @ParameterizedTest
    @MethodSource
    fun detect(row: Int, prevValues: String, values: String, nextValues: String, lowPoints: Array<LowPoint>) {
        val prevValuesList = splitString(prevValues)
        val nextValuesList = splitString(nextValues)
        val valuesList = splitString(values)
        val result = lowPointDetector.detect(row, prevValuesList, valuesList, nextValuesList)
        assertArrayEquals(lowPoints, result)
    }

    companion object {
        @JvmStatic
        fun detect() : Stream<Arguments> =
            Stream.of(
                Arguments.of(
                    0,
                    "",
                    "2199943210",
                    "3987894921",
                    arrayOf(LowPoint(1, 0, 1), LowPoint(9,0,0))),
                Arguments.of(
                    1,
                    "2199943210",
                    "3987894921",
                    "9856789892",
                    arrayOf<LowPoint>()
                ),
                Arguments.of(
                    2,
                    "3987894921",
                    "9856789892",
                    "8767896789",
                    arrayOf(LowPoint(2, 2,5))),
                Arguments.of(
                    3,
                    "9856789892",
                    "8767896789",
                    "9899965678",
                    arrayOf<LowPoint>()),
                Arguments.of(
                    4,
                    "8767896789",
                    "9899965678",
                    "",
                    arrayOf(LowPoint(6, 4,5))))
    }

    @Test
    fun detectAll() {
        val result = lowPointDetector.detect(create())
        assertEquals(4, result.size)
    }

    private fun create(): Array<Array<Int>> {
        val input = listOf("2199943210","3987894921","9856789892","8767896789", "9899965678")
        return input.map { splitString(it).toTypedArray() }.toTypedArray()
    }
}


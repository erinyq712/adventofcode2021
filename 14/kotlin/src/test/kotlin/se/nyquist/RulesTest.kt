package se.nyquist

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class RulesTest {
    private val ruleMap = mapOf(
        Pair("CH",'B'),
        Pair("HH",'N'),
        Pair("CB",'H'),
        Pair("NH",'C'),
        Pair("HB",'C'),
        Pair("HC",'B'),
        Pair("HN",'C'),
        Pair("NN",'C'),
        Pair("BH",'H'),
        Pair("NC",'B'),
        Pair("NB",'B'),
        Pair("BN",'B'),
        Pair("BB",'N'),
        Pair("BC",'B'),
        Pair("CC",'N'),
        Pair("CN",'C')
    )
    private val rules = Rules(ruleMap)

    @Test
    fun test0() {
        val charMap: Map<Char,Long> = rules.getDistribution(0, "CN")
        assertEquals(1, charMap['C'])
        assertEquals(1, charMap['N'])
    }

    @Test
    fun test1() {
        val charMap: Map<Char,Long> = rules.getDistribution(1, "CN")
        assertEquals(2, charMap['C'])
        assertEquals(1, charMap['N'])
    }

    @Test
    fun test2() {
        val charMap: Map<Char,Long> = rules.getDistribution(2, "CN")
        assertEquals(3, charMap['C'])
        assertEquals(2, charMap['N'])
    }

    @Test
    fun test05() {
        val charMap: Map<Char,Long> = rules.getDistribution(0, "CN", 5)
        assertEquals(5, charMap['C'])
        assertEquals(5, charMap['N'])
    }

    @Test
    fun test15() {
        val charMap: Map<Char,Long> = rules.getDistribution(1, "CN", 5)
        assertEquals(10, charMap['C'])
        assertEquals(5, charMap['N'])
    }

    @Test
    fun test25() {
        val charMap: Map<Char,Long> = rules.getDistribution(2, "CN", 5)
        assertEquals(15, charMap['C'])
        assertEquals(10, charMap['N'])
    }
}
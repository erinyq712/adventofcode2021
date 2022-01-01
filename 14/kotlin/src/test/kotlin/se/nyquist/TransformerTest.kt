package se.nyquist

import org.junit.jupiter.api.Test
import kotlin.test.Ignore
import kotlin.test.assertEquals

class TransformerTest {
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
    private val transformer = Transformer(rules)

    @Test
    fun test0() {
        val input = "NNCB"
        val transformed = transformer.transform(0, input)
        assertEquals(input, transformed)
        val distribution = transformer.getDistribution(0, input)
        assertEquals(1, distribution['C'])
        assertEquals(2, distribution['N'])
    }

    @Test
    fun test1() {
        val input = "NNCB"
        val transformed = transformer.transform(1, input)
        assertEquals("NCNBCHB", transformed)
        val distribution = transformer.getDistribution(1, input)
        assertEquals(2, distribution['C'])
        assertEquals(2, distribution['N'])
    }

    @Test
    fun test10() {
        val input = "NNCB"
        val distribution = transformer.getDistribution(10, input)
        assertEquals(1749, distribution['B'])
        assertEquals(161, distribution['H'])
    }

    @Test
    fun test15() {
        val input = "NNCB"
        val distribution = transformer.getDistribution(15, input)
        assertEquals(60184, distribution['B'])
        assertEquals(3292, distribution['H'])
    }

    @Test
    fun test20() {
        val input = "NNCB"
        val distribution = transformer.getDistribution(20, input)
        assertEquals(2009315, distribution['B'])
        assertEquals(47997, distribution['H'])
    }

    @Test
    fun test40() {
        val input = "NNCB"
        val distribution = transformer.getDistribution(40, input)
        assertEquals(2192039569602, distribution['B'])
        assertEquals(3849876073, distribution['H'])
    }
}
package se.nyquist

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class BitsEncoderTest {

    @Test
    fun testLiteral() {
        val encoder = BitsEncoder("D2FE28")
        assertTrue(encoder.hasNext())
        val packet = encoder.next()
        assertFalse(encoder.hasNext())
        assertTrue(packet is LiteralPacket)
        assertFalse(packet.hasSubPackets())
        val literal : LiteralPacket = packet
        assertEquals("11111100101", literal.value().toString(radix = 2))
    }

    @Test
    fun testGreaterThanWithLength() {
        val encoder = BitsEncoder("38006F45291200")
        assertTrue(encoder.hasNext())
        val packet = encoder.next()
        // Trailing zeros...
        // assertFalse(encoder.hasNext())
        assertTrue(packet is OperatorPacket)
        assertTrue(packet.hasSubPackets())
        val operator : OperatorPacket = packet
        val packets = operator.getSubPackets()
        assertEquals(10, (packets[0] as LiteralPacket).value())
        assertEquals(20, (packets[1] as LiteralPacket).value())
        assertEquals(0, packet.value())
    }

    @Test
    fun testMaximumWithPackets() {
        val encoder = BitsEncoder("EE00D40C823060")
        assertTrue(encoder.hasNext())
        val packet = encoder.next()
        // assertFalse(encoder.hasNext())
        assertTrue(packet is OperatorPacket)
        assertTrue(packet.hasSubPackets())
        val operator : OperatorPacket = packet
        val packets = operator.getSubPackets()
        assertEquals(1, (packets[0] as LiteralPacket).value())
        assertEquals(2, (packets[1] as LiteralPacket).value())
        assertEquals(3, (packets[2] as LiteralPacket).value())
        assertEquals(3, packet.value())
    }

    @Test
    fun testMinimum() {
        val encoder = BitsEncoder("8A004A801A8002F478")
        assertTrue(encoder.hasNext())
        val packet = encoder.next()
        // assertFalse(encoder.hasNext())
        assertTrue(packet is OperatorPacket)
        assertTrue(packet.hasSubPackets())
        assertEquals(15, packet.value())
    }

    @Test
    fun testSum() {
        val encoder = BitsEncoder("620080001611562C8802118E34")
        assertTrue(encoder.hasNext())
        val packet = encoder.next()
        // assertFalse(encoder.hasNext())
        assertTrue(packet is OperatorPacket)
        assertTrue(packet.hasSubPackets())
        assertEquals(46, packet.value())
    }

    @Test
    fun testSumWithPacketSize() {
        val encoder = BitsEncoder("C0015000016115A2E0802F182340")
        assertTrue(encoder.hasNext())
        val packet = encoder.next()
        // assertFalse(encoder.hasNext())
        assertTrue(packet is OperatorPacket)
        assertTrue(packet.hasSubPackets())
        assertEquals(46, packet.value())
    }

    @Test
    fun testAnotherSum() {
        val encoder = BitsEncoder("A0016C880162017C3686B18A3D4780")
        assertTrue(encoder.hasNext())
        val packet = encoder.next()
        // assertFalse(encoder.hasNext())
        assertTrue(packet is OperatorPacket)
        assertTrue(packet.hasSubPackets())
        assertEquals(54, packet.value())
    }

    @Test
    fun testProduct() {
        val encoder = BitsEncoder("04005AC33890")
        assertTrue(encoder.hasNext())
        val packet = encoder.next()
        // assertFalse(encoder.hasNext())
        assertTrue(packet is OperatorPacket)
        assertTrue(packet.hasSubPackets())
        assertEquals(54, packet.value())
    }

    @Test
    fun testMinimum2() {
        val encoder = BitsEncoder("880086C3E88112")
        assertTrue(encoder.hasNext())
        val packet = encoder.next()
        // assertFalse(encoder.hasNext())
        assertTrue(packet is OperatorPacket)
        assertTrue(packet.hasSubPackets())
        assertEquals(7, packet.value())
    }

    @Test
    fun testMaximum2() {
        val encoder = BitsEncoder("CE00C43D881120")
        assertTrue(encoder.hasNext())
        val packet = encoder.next()
        // assertFalse(encoder.hasNext())
        assertTrue(packet is OperatorPacket)
        assertTrue(packet.hasSubPackets())
        assertEquals(9, packet.value())
    }

    @Test
    fun testLessThan2() {
        val encoder = BitsEncoder("D8005AC2A8F0")
        assertTrue(encoder.hasNext())
        val packet = encoder.next()
        // assertFalse(encoder.hasNext())
        assertTrue(packet is OperatorPacket)
        assertTrue(packet.hasSubPackets())
        assertEquals(1, packet.value())
    }

    @Test
    fun testGreaterThan2() {
        val encoder = BitsEncoder("F600BC2D8F")
        assertTrue(encoder.hasNext())
        val packet = encoder.next()
        // assertFalse(encoder.hasNext())
        assertTrue(packet is OperatorPacket)
        assertTrue(packet.hasSubPackets())
        assertEquals(0, packet.value())
    }

    @Test
    fun testEquals() {
        val encoder = BitsEncoder("9C005AC2F8F0")
        assertTrue(encoder.hasNext())
        val packet = encoder.next()
        // assertFalse(encoder.hasNext())
        assertTrue(packet is OperatorPacket)
        assertTrue(packet.hasSubPackets())
        assertEquals(0, packet.value())
    }

    @Test
    fun testEquals3() {
        val encoder = BitsEncoder("9C0141080250320F1802104A08")
        assertTrue(encoder.hasNext())
        val packet = encoder.next()
        // assertFalse(encoder.hasNext())
        assertTrue(packet is OperatorPacket)
        assertTrue(packet.hasSubPackets())
        assertEquals(1, packet.value())
    }

}
package se.nyquist

abstract class OperatorPacket(encoder: BitsEncoder) : Packet {
    private val packets = if (encoder.hasNext()) encoder.getSubPackets() else listOf()

    override fun hasSubPackets(): Boolean {
        return true
    }

    override fun getSubPackets(): List<Packet> {
        return packets
    }

}

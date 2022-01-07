package se.nyquist

data class LiteralPacket(val encoder: BitsEncoder) : Packet {
    private val data : Long = if (encoder.hasNext()) encoder.getLiteral() else 0

    override fun hasSubPackets(): Boolean {
        return false
    }

    override fun value() : Long {
        return data
    }
}
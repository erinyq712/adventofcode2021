package se.nyquist

class MinimumPacket(encoder: BitsEncoder)  : OperatorPacket(encoder) {
    override fun value(): Long {
        return getSubPackets().minOf { it.value() }
    }
}
package se.nyquist

class MaximumPacket(encoder: BitsEncoder)  : OperatorPacket(encoder) {
    override fun value(): Long {
        return getSubPackets().maxOf { it.value() }
    }
}
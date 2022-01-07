package se.nyquist

class SumPacket(encoder: BitsEncoder)  : OperatorPacket(encoder) {
    override fun value(): Long {
        return getSubPackets().sumOf { it.value() }
    }
}
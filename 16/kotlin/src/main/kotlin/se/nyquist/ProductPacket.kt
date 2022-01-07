package se.nyquist

class ProductPacket(encoder: BitsEncoder)  : OperatorPacket(encoder) {
    override fun value(): Long {
        return getSubPackets().fold(1) { acc, it -> acc * it.value() }
    }
}
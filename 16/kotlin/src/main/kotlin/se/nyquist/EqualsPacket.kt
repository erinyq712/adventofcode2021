package se.nyquist

class EqualsPacket(encoder: BitsEncoder)  : OperatorPacket(encoder) {
    override fun value(): Long {
        return if (getSubPackets()[0].value() == getSubPackets()[1].value()) 1 else 0
    }
}
package se.nyquist

import java.lang.RuntimeException

class PacketFactory(private val encoder: BitsEncoder) {
    fun nextPacket(type: Int): Packet {
        return if (type == 4) {
            LiteralPacket(encoder)
        } else if (type == 0) {
            SumPacket(encoder)
        } else if (type == 1) {
            ProductPacket(encoder)
        } else if (type == 2) {
            MinimumPacket(encoder)
        } else if (type == 3) {
            MaximumPacket(encoder)
        } else if (type == 5) {
            GreaterThanPacket(encoder)
        } else if (type == 6) {
            LessThanPacket(encoder)
        } else if (type == 7) {
            EqualsPacket(encoder)
        } else throw RuntimeException("Unexpected")
    }
}
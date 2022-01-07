package se.nyquist

interface Packet {
    fun hasSubPackets() : Boolean
    fun getSubPackets() : List<Packet> = listOf()
    fun value() : Long
}
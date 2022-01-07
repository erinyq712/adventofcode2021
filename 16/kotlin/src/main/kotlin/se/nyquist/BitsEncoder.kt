package se.nyquist

import kotlin.properties.Delegates.observable

class BitsEncoder(input: String) {
    private val bits: String =
        input.map{String.format("%4s", Integer.toBinaryString(it.digitToInt(16))).replace(" ","0")}.joinToString("")

    private val factory : PacketFactory = PacketFactory(this)

    private val accumulators = mutableListOf<Accumulator>()
    val versionCounter = VersionCounter()

    private var position : Int by observable(0) { _, oldValue, newValue -> accumulators.forEach { it.incrementBy(newValue-oldValue ) }}

    init {
        println(bits)
    }

    fun getVersion(): Int {
        val data = getInt(3)
        versionCounter.add(data)
        return data
    }

    fun getType(): Int {
        return getInt(3)
    }

    fun getLengthTypeId(): Int {
        return getInt(1)
    }

    fun isLastChunk() : Boolean {
        return getInt(1) == 0
    }

    private fun getInt(size: Int) : Int {
        val data = bits.substring(position, position + size).toInt(radix = 2)
        incPosition(size)
        return data
    }

    fun hasNext(): Boolean {
        return position < bits.length
    }

    fun next(): Packet {
        val accumulator = Accumulator()
        accumulator.start(this)
        getVersion()
        val type = getType()
        val data = nextSubPacket(type)
        accumulator.stop(this)
        incPosition(4 - accumulator.counter % 4)
        return data
    }

    fun nextSubPacket(): Packet {
        getVersion()
        val type = getType()
        val packet = factory.nextPacket(type)
        return packet
    }

    fun nextSubPacket(type: Int): Packet {
        val packet = factory.nextPacket(type)
        return packet
    }

    fun getLiteral(): Long {
        val valueBits = StringBuilder()
        if (hasNext()) {
            do {
                val isComplete = isLastChunk()
                valueBits.append(getDataBits())
            } while(! isComplete)
        }
        return valueBits.toString().toLong(radix = 2)
    }

    private fun getDataBits(): String {
        val data = bits.substring(position, position+4)
        incPosition(4)
        return data
    }

    fun getSubPackets(): List<Packet> {
        val packets = mutableListOf<Packet>()
        if (hasNext()) {
            if (getLengthTypeId() == 0) {
                val length = getLength()
                val accumulator = Accumulator()
                accumulator.start(this)
                val packet = nextSubPacket()
                packets.add(packet)
                if (accumulator.counter < length) {
                    do {
                        val subpacket = nextSubPacket()
                        packets.add(subpacket)
                    } while(accumulator.counter < length)
                }
                accumulator.stop(this)
            } else {
                var packetCount = getPackets()
                while(packetCount > 0) {
                    val packet = nextSubPacket()
                    packets.add(packet)
                    packetCount--
                }
            }
        }
        return packets
    }

    private fun incPosition(size: Int) {
        position += size
    }

    private fun getLength(): Int {
        return getInt(15)
    }

    private fun getPackets(): Int {
        return getInt(11)
    }

    fun addAccumulator(accumulator: Accumulator) {
        accumulators.add(accumulator)
    }

    fun removeAccumulator(accumulator: Accumulator) {
        accumulators.remove(accumulator)
    }
}
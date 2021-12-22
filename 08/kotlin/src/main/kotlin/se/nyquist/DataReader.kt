package se.nyquist

import java.io.File
import java.util.*

class DataReader(fileName: String) {
    private val distribution : MutableMap<Digit,Int>
    private val fileName : String

    init {
        this.distribution = EnumMap(Digit::class.java)
        this.fileName = fileName
    }

    private fun countUniquePatterns(fileName: String) {
        val file = File(fileName)
        val lines = file.readLines()
        for (line in lines) {
            val entry = line.split("|")
            val input = entry[0].split(" ")
            for (pattern in input) {
                val digits = matchDigitsOnPattern(pattern)
                if (digits.size == 1) {
                    val key = digits[0]
                    if (distribution.containsKey(key)) {
                        val current = distribution.getValue(key)
                        distribution.replace(digits[0], current + 1)
                    } else {
                        distribution[key] = 1
                    }
                }
            }
        }
    }

    fun decodeAndAddAll() : Int {
        val file = File(fileName)
        val lines = file.readLines()
        val result = mutableListOf<Int>()
        for (line in lines) {
            val entry = line.split(" | ")
            val input = entry[0].split(" ")
            val output = entry[1].split(" ")
            val decoder = Decoder()
            val decodedValue = decoder.decode(input, output)
            println(decodedValue)
            result.add(decodedValue)
        }
        return result.sum()
    }

    fun getDistribution() : Map<Digit,Int> {
        countUniquePatterns(fileName)
        return distribution
    }
}
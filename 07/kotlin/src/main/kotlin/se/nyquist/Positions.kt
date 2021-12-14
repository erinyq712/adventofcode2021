package se.nyquist

import java.io.File
import java.lang.Math.abs

class Positions(fileName: String) {
    init {
        val file = File(fileName)
        val lines = file.readLines()
        val values = lines.first().split(",").map { it.toInt() }
        val maxValue = values.maxOrNull()
        val map = values.groupingBy { it }.eachCount()
        val sumMap = HashMap<Int,Long>()
        for (i in 0..maxValue!!) {
            val modMap = HashMap<Int,Long>()
            for (key in map.keys) {
                val count = map.getValue(key)
                val diff = abs(i-key).toLong()
                modMap.put(key, count * diff)
            }
            val sum = modMap.values.sum()
            sumMap.put(i, sum)
            println("Pos: ${i} Sum: ${sum}")
        }
        val minimum = sumMap.entries.sortedBy { it.value }[0]
        println("Minimum: ${minimum.key} ${minimum.value}")
    }
}

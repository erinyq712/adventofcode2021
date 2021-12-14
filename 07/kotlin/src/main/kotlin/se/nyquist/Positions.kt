package se.nyquist

import java.io.File

class Positions(fileName: String, calculator: Calculator) {

    private val sumMap = HashMap<Int,Long>()
    private val calculator : Calculator

    init {
        this.calculator = calculator
        val file = File(fileName)
        val lines = file.readLines()
        val values = lines.first().split(",").map { it.toInt() }
        val maxValue = values.maxOrNull()
        val map = values.groupingBy { it }.eachCount()

        for (i in 0..maxValue!!) {
            val modMap = HashMap<Int,Long>()
            for (key in map.keys) {
                modMap.put(key, calculator.getCost(i, key, map.getValue(key)))
            }
            val sum = modMap.values.sum()
            sumMap.put(i, sum)
            println("Pos: ${i} Sum: ${sum}")
        }
    }

    fun getMinimumEntry() : MutableMap.MutableEntry<Int, Long> {
        return sumMap.entries.sortedBy { it.value }[0]
    }
}

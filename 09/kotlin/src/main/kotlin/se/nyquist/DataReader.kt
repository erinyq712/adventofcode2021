package se.nyquist

import java.io.File

class DataReader(fileName: String) {
    val file = File(fileName)
    val lines = file.readLines()
    val lowPoints = mutableListOf<LowPoint>()
    val basins : List<Basin>

    init {
        val lpd = LowPointDetector()
        val input = mutableListOf<Array<Int>>()
        var prev = listOf<Int>()
        var next = splitString(lines[0])
        var current : List<Int>
        for (i in lines.indices) {
            current = next
            input.add(current.toTypedArray())
            next = if (i < lines.size-1) splitString(lines[i+1]) else listOf<Int>()
            lpd.detect(i, prev, current, next).forEach { lowPoints.add(it) }
            prev = current
        }
        val basinFinder = BasinFinder(lowPoints.toTypedArray(), input.toTypedArray())
        basins = basinFinder.find()
    }
}

fun splitString(input : String) : List<Int> {
    if (input.isEmpty()) {
        return listOf()
    }
    return IntRange(0, input.length-1).map{ input.substring(it,it+1).toInt() }.toList()
}


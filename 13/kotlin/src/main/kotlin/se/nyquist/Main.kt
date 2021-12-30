package se.nyquist
import java.io.File

fun main() {

    val lines = File("input.txt").readLines()
    val coordinates = lines.filter{ it.contains(',')}.map { getPair(it) }.toList()
    val instructionsLines = lines.filter{ it.contains('=')}.map { it.split(" ")[2].split("=") }.map { Pair(it[0], it[1]) }
    exercise1(coordinates, instructionsLines.toMutableList())
    exercise2(coordinates, instructionsLines.toMutableList())
}

fun getPair(it: String): Pair<Int, Int> {
    val c = it.split(",")
    return Pair(c[0].toInt(), c[1].toInt())
}

private fun exercise1(values: List<Pair<Int, Int>>, commands: MutableList<Pair<String, String>>) {
    var currentValues : List<Pair<Int, Int>> = values.toList()
    val current = commands.removeAt(0)
    if (current.first == "y") {
        currentValues = foldVertical(currentValues, current.second.toInt())
    } else if (current.first == "x") {
        currentValues = foldHorizontal(currentValues, current.second.toInt())
    }
    println("Dots: ${currentValues.size}")
}

private fun getPrintChar (
    currentValues: List<Pair<Int, Int>>,
    col: Int,
    row: Int
) : Char = if (currentValues.contains(Pair(col, row)))  '*' else ' '

fun foldVertical(values: List<Pair<Int, Int>>, c: Int): List<Pair<Int, Int>> {
    return values.map { transformVertical(it, c) }.distinct().toList()
}

fun transformVertical(it: Pair<Int, Int>, c: Int) : Pair<Int, Int> {
    val vdiff = it.second - c
    if (vdiff > 0) {
        return Pair(it.first, c - vdiff)
    }
    return Pair(it.first, it.second)
}

fun foldHorizontal(values: List<Pair<Int, Int>>, c: Int): List<Pair<Int, Int>> {
    return values.map { transformHorizontal(it, c) }.distinct().toList()
}

fun transformHorizontal(it: Pair<Int, Int>, c: Int) : Pair<Int, Int> {
    val hdiff = it.first - c
    if (hdiff > 0) {
        return Pair(c - hdiff, it.second)
    }
    return Pair(it.first, it.second)
}

private fun exercise2(values: List<Pair<Int, Int>>, commands: MutableList<Pair<String, String>>) {
    var currentValues : List<Pair<Int, Int>> = values.toList()
    while (commands.isNotEmpty()) {
        val current = commands.removeAt(0)
        if (current.first == "y") {
            currentValues = foldVertical(currentValues, current.second.toInt())
        } else if (current.first == "x") {
            currentValues = foldHorizontal(currentValues, current.second.toInt())
        }
        val xmax = currentValues.maxOf { it.first }
        val ymax = currentValues.maxOf { it.second }
        println("X: $xmax Y; $ymax")
    }
    val xmax = currentValues.maxOf { it.first }
    val ymax = currentValues.maxOf { it.second }
    IntRange(0,ymax).forEach { row -> println(IntRange(0, xmax).map {
            col -> getPrintChar(currentValues, col, row)
        }.joinToString(""))
    }
}


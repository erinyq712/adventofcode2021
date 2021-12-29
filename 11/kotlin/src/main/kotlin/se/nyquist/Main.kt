import se.nyquist.OctopusGrid
import java.io.File

fun main() {

    val lines = File("input.txt").readLines()
    val values = lines.map { it.map { v -> v.digitToInt() }.toIntArray()}.toTypedArray()
    exercise1(values)
    exercise2(values)
}
private fun exercise1(values: Array<IntArray>) {
    val grid = OctopusGrid(values)
    var counter = 0
    for (i in 0..99) {
        grid.next()
        counter += grid.count
    }
    println("Flashed: $counter")
}

private fun exercise2(values: Array<IntArray>) {
    val octopusCount = values.indices.sumOf { values[it].count() }
    val grid = OctopusGrid(values)
    var counter = 0L
    var step = 0
    while (grid.count < octopusCount){
        step++
        grid.next()
        if (grid.count == octopusCount) {
            println("All flashed: $step")
        }
        counter += grid.count
    }
    println("Flashed: $counter")
}

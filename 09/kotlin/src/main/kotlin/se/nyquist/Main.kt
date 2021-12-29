import se.nyquist.DataReader

fun main(args: Array<String>) {
    val fileName = "input.txt"
    val reader = DataReader(fileName)
    exercise1(reader)
    exercise2(reader)
}

fun exercise1(reader: DataReader) {
    val count = reader.lowPoints.size
    println("Count: ${count}")
    val summa = reader.lowPoints.map { it.value }.sum()
    println("Summa: ${summa}")
    val result = reader.lowPoints.map { it.value + 1 }.sum()
    println("Result: ${result}")
}

fun exercise2(reader: DataReader) {
    val basins = reader.basins.sortedByDescending { it.size }
    val product = basins[0].size * basins[1].size * basins[2].size
    println("Largest basins product: ${product}")
}
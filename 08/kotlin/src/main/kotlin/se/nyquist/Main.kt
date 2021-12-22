package se.nyquist

fun main() {
    val fileName = "input.txt"
    // exercise1(fileName)
    exercise2(fileName)

}

private fun exercise1(fileName: String) {
    val reader = DataReader(fileName)

    for (entry in reader.getDistribution().entries.sortedBy { it.key.number }) {
        println("${entry.key}: ${entry.value}")
    }
    val sum = reader.getDistribution().entries.map { it.value }.sum()
    println("Summa: ${sum}")
}

private fun exercise2(fileName: String) {
    val reader = DataReader(fileName)
    val sum = reader.decodeAndAddAll()
    println("Sum: ${sum}")
}
package se.nyquist

fun main() {
    val fileName = "input.txt"
    val p = Positions(fileName, SimpleCalculator())
    val minimum = p.getMinimumEntry()
    println("Minimum: ${minimum.key} ${minimum.value}")

    val p2 = Positions(fileName, AdvancedCalculator())
    val minimum2 = p2.getMinimumEntry()
    println("Minimum: ${minimum2.key} ${minimum2.value}")
}
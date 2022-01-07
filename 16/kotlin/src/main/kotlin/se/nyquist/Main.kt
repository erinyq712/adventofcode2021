package se.nyquist

import java.io.File

fun main() {
    val data = File("input.txt").readLines()
    exercise1(data)
    exercise2(data)
}

fun exercise1(data: List<String>) {
    val encoder = BitsEncoder(data[0])
    encoder.next()
    println("Version numbers sum: ${encoder.versionCounter.counter}")
}

fun exercise2(data: List<String>) {
    val encoder = BitsEncoder(data[0])
    val packet = encoder.next()
    println("Value ${packet.value()}")
}

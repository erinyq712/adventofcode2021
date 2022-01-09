package se.nyquist

import java.io.File
import kotlin.math.absoluteValue

fun readRange(input: String): IntRange {
    val xCoordStr = input.split("..")
    return IntRange(xCoordStr[0].toInt(), xCoordStr[1].toInt())
}

fun main() {
    val ranges = readInput("input.txt")
    val targetArea = TargetArea(ranges)
    val hits = mutableListOf<Pair<Int, Int>>()

    var xVelocity = if (targetArea.xRange.first > 0) 1 else -1
    var yVelocity = targetArea.yRange.first
    var maxPos = 0
    var prevWasHit = false
    while (true) {
        val xLimit = xVelocity * (xVelocity + 1) / 2
        if (xLimit > targetArea.xRange.first) {
            var thisIsHit = false
            val startVelocity = Pair(xVelocity, yVelocity)
            val startPosition = Pair(0, 0)
            val state = Probe(targetArea, startPosition, startVelocity)
            if (state.search(hits)) {
                thisIsHit = true
                if (state.maxPos > maxPos)
                    maxPos = state.maxPos
                println("$startVelocity $state")
            } else {
                if (yVelocity > targetArea.yRange.first.absoluteValue) {
                    break
                }
            }
            if (!thisIsHit && prevWasHit || xVelocity+1 > targetArea.xRange.last) {
                xVelocity = if (targetArea.xRange.first > 0) 1 else -1
                yVelocity++
                // prevWasHit = false
            } else {
                xVelocity = if (targetArea.xRange.first > 0) xVelocity+1 else xVelocity-1
            }
            prevWasHit = thisIsHit
        } else {
            if (xVelocity+1 > targetArea.xRange.last) {
                xVelocity = if (targetArea.xRange.first > 0) 1 else -1
                yVelocity++
                // prevWasHit = false
            } else {
                xVelocity = if (targetArea.xRange.first > 0) xVelocity+1 else xVelocity-1
            }
            prevWasHit = false
        }
    }
    println("$maxPos")
    println("${hits.count()}")
}

private fun readInput(input: String): Array<IntRange> {
    val line = File(input).readText()
    val data = line.split((":"))
    val ranges = data[1].split(",")
    val xRangeStr = ranges[0].split("=")
    val yRangeStr = ranges[1].split("=")
    return arrayOf(readRange(xRangeStr[1]), readRange(yRangeStr[1]))
}
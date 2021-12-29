package se.nyquist
import java.io.File

fun main() {
    val lines = File("input.txt").readLines()
    val values = lines.map { it.split("-") }.toList()
    val pairs = values.map { Pair(it[0], it[1] )}.union(values.map { Pair(it[1], it[0] )})
    exercise1(pairs.distinct().toList())
    exercise2(pairs.distinct().toList())
}

fun exercise1(pairs: List<Pair<String, String>>) {
    val paths = buildAllPaths(pairs.distinct().toList())
    println("Number of paths: ${paths.size}")
}

fun exercise2(pairs: List<Pair<String, String>>) {
    val paths = buildAllPaths2(pairs.distinct().toList())
    println("Number of paths: ${paths.size}")
}

fun buildAllPaths(values: List<Pair<String, String>>) : List<String> {
    val startNodes = values.filter { it.first == "start"}.distinct().toList()
    return startNodes.flatMap { findPathFrom(it, values, mutableListOf(it.first)) }.distinct()
}

fun findPathFrom(current: Pair<String, String>, values: List<Pair<String, String>>, visited : MutableList<String>) : List<String> {
    if (visited.contains(current.second) && current.second == current.second.lowercase()) {
        return listOf()
    }
    visited.add(current.second)
    val next = values.filter { it.first == current.second && (it.second == it.second.uppercase() || ! visited.contains(it.second)) }
    val result = mutableListOf<String>()
    if (next.isNotEmpty()) {
        next.forEach {
            if (it.second != "end") {
                val found = findPathFrom(it, values, visited)
                if (found.isNotEmpty()) {
                    result.addAll(found)
                }
            } else {
                visited.add(it.second)
                val path = visited.joinToString("-")
                // println(path)
                result.add(path)
                visited.removeLast()
            }
        }
    }
    visited.removeLast()
    return result.distinct().toList()
}

fun buildAllPaths2(values: List<Pair<String, String>>) : List<String> {
    val startNodes = values.filter { it.first == "start"}.distinct().toList()
    return startNodes.flatMap { findPathFrom2(it, values, mutableListOf(it.first)) }.distinct()
}

fun findPathFrom2(current: Pair<String, String>, values: List<Pair<String, String>>, visited : MutableList<String>) : List<String> {
    visited.add(current.second)
    if (! isAcceptable(current, visited)) {
        visited.removeLast()
        return listOf()
    }
    val next = values.filter { it.first == current.second && isAcceptable(it, visited) }
    val result = mutableListOf<String>()
    if (next.isNotEmpty()) {
        next.forEach {
            if (it.second != "end") {
                val found = findPathFrom2(it, values, visited)
                if (found.isNotEmpty()) {
                    result.addAll(found)
                }
            } else {
                visited.add(it.second)
                val path = visited.joinToString("-")
                // println(path)
                result.add(path)
                visited.removeLast()
            }
        }
    }
    visited.removeLast()
    return result.distinct().toList()
}

private fun isAcceptable(
    it: Pair<String, String>,
    visited: MutableList<String>
) : Boolean {
    val isBigCave = it.second == it.second.uppercase()
    val occurrenceMap =  visited.filter { it == it.lowercase() }.groupingBy { it }.eachCount().filterNot { it.value == 1 }
    return isBigCave || (it.second != "start" && occurrenceMap.size <= 1 && occurrenceMap.none{ it.value > 2 })
}
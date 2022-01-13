package se.nyquist

import java.io.File

fun main() {
    //val input = "[[[[1,3],[5,3]],[[1,3],[8,7]]],[[[4,9],[6,9]],[[8,2],[7,3]]]]"
    //val input = "[[[[[4,3],4],4],[7,[[8,4],9]]],[1,1]]"
    val lines = File("input.txt").readLines()
    val number = lines.map{ parse(it) }.reduce(SnailfishNumber::plus)
    println(number.magnitude())
}



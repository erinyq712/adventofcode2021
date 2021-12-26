package se.nyquist

class BasinFinder(private val lowPoints: Array<LowPoint>, private val values: Array<Array<Int>>) {

    fun find() : List<Basin> {
        val basins = mutableListOf<Basin>()
        for( lowpoint in lowPoints) {
            val basinPoints: List<Pair<Int, Int>> = findAdjacent(Pair(lowpoint.row, lowpoint.position))
            basins.add(Basin(basinPoints))
        }
        return basins
    }

    private fun findAdjacent(pair: Pair<Int, Int>): List<Pair<Int, Int>> {
        val visited = mutableListOf<Pair<Int, Int>>()
        visited.add(pair)
        findAdjacent(pair, visited)
        return visited
    }

    private fun findAdjacent(pair: Pair<Int, Int>, visited: MutableList<Pair<Int,Int>>) {
        findUpward(pair, visited)
        findDownward(pair, visited)
        findBackward(pair, visited)
        findForward(pair, visited)
    }

    private fun findUpward(pair: Pair<Int, Int>, visited: MutableList<Pair<Int, Int>>) {
        if (pair.second == 0) {
            return
        }
        val currentValue = values[pair.first][pair.second]
        val next = Pair(pair.first, pair.second-1)
        checkValue(next, currentValue, visited)
    }

    private fun findDownward(pair: Pair<Int, Int>, visited: MutableList<Pair<Int, Int>>) {
        if (pair.second == values[0].lastIndex) {
            return
        }
        val currentValue = values[pair.first][pair.second]
        val next = Pair(pair.first, pair.second+1)
        checkValue(next, currentValue, visited)
    }

    private fun findBackward(pair: Pair<Int, Int>, visited: MutableList<Pair<Int, Int>>) {
        if (pair.first == 0) {
            return
        }
        val currentValue = values[pair.first][pair.second]
        val next = Pair(pair.first-1, pair.second)
        checkValue(next, currentValue, visited)
    }

    private fun findForward(pair: Pair<Int, Int>, visited: MutableList<Pair<Int, Int>>) {
        if (pair.first == values.lastIndex) {
            return
        }
        val currentValue = values[pair.first][pair.second]
        val next = Pair(pair.first+1, pair.second)
        checkValue(next, currentValue, visited)
    }

    private fun checkValue(
        next: Pair<Int, Int>,
        currentValue: Int,
        visited: MutableList<Pair<Int, Int>>
    ) {
        val nextValue = values[next.first][next.second]
        if (nextValue != 9 && nextValue >= currentValue &&  !visited.contains(next)) {
            visited.add(next)
            findAdjacent(next, visited)
        }
    }
}
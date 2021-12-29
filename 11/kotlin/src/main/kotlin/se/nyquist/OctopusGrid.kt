package se.nyquist

class OctopusGrid(values : Array<IntArray>) {
    private var next : Array<IntArray> = values.map { row -> row.map { it }.toIntArray() }.toTypedArray()
    var count = 0

    fun next() : Array<IntArray> {
        next = next.map { it.map { col -> col + 1 }.toIntArray() }.toTypedArray()
        var flashed = Array(next.size) { it -> next[it].map { it > 9 }.toBooleanArray() }
        val flashedIndices = flashed.indices.flatMap { flashed[it].indices.mapNotNull { col -> createPair(it,col,flashed)} }.toMutableList()
        while(flashedIndices.isNotEmpty()) {
            val current = flashedIndices.removeAt(0)
            updateLevels(next, flashed, current)
            val flashed2 = Array(next.size) { it -> next[it].map { it > 9 }.toBooleanArray() }
            val newFlashed = xor(flashed, flashed2)
            flashed = flashed2
            flashedIndices.addAll(newFlashed.indices.flatMap { newFlashed[it].indices.mapNotNull { col -> createPair(it,col,newFlashed)} })
        }
        count = flashed.indices.sumOf { flashed[it].count { col -> col } }
        resetFlashed()
        return next
    }

    private fun resetFlashed() {
        next.indices.forEach{ row -> next[row].indices.forEach { col -> if (next[row][col] > 9) next[row][col] = 0 }}
    }

    private fun xor(flashed: Array<BooleanArray>, flashed2: Array<BooleanArray>) : Array<BooleanArray> {
        return Array(flashed.size) { row -> flashed[row].indices.map { flashed[row][it].xor(flashed2[row][it])}.toBooleanArray() }
    }

    // Update after flash
    private fun updateLevels(next: Array<IntArray>, flashed: Array<BooleanArray>, pair: Pair<Int, Int>) : List<Pair<Int, Int>> {

        // Update matrix with levels
        if (pair.first > 0) {
            val beforeRow = next[pair.first-1]
            beforeRow.indices.filter{ it <= pair.second+1 && it >= pair.second-1 }.forEach { beforeRow[it]++ }
        }
        val currentRow = next[pair.first]
        currentRow.indices.filter{ it == pair.second+1 || it == pair.second-1 }.forEach { currentRow[it]++ }
        if (pair.first < next.lastIndex) {
            val afterRow = next[pair.first+1]
            afterRow.indices.filter{ it <= pair.second+1 && it >= pair.second-1 }.forEach { afterRow[it]++ }
        }
        val flashed2 = Array(next.size) { it -> next[it].map { it >= 9 }.toBooleanArray() }
        val newFlashed = xor(flashed, flashed2)
        return newFlashed.indices.flatMap { newFlashed[it].indices.mapNotNull { col -> createPair(it,col,newFlashed)} }
    }

    private fun createPair(row: Int, col: Int, flashed: Array<BooleanArray>) : Pair<Int, Int>? {
        if (flashed[row][col]) {
            return Pair(row,col)
        }
        return null
    }

}
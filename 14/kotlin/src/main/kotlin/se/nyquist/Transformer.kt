package se.nyquist

class Transformer(private val rules: Rules) {
    fun transform(i: Int, input: String): String {
        return when (i) {
            0 -> {
                input
            }
            1 -> {
                next(input)
            }
            else -> {
                transform(i-1, next(input))
            }
        }
    }

    fun next(template: String) : String {
        val pairs = getPairs(template).toList()
        return pairs.joinToString("") { it[0] + rules.getValue(it) } + pairs[pairs.lastIndex][1]
    }

    fun getDistribution(depth : Int, input: String): Map<Char, Long>  {
        val pairs = getPairs(input).groupingBy { it }.fold(0L){ it, _ -> it + 1L}
        val lastChar = input.last()
        return if (depth == 0) {
            pairs
                .map { it.key.first() to it.value }
                .groupBy({ it.first }, { it.second })
                .mapValues { it.value.sum() + if (it.key == lastChar) 1 else 0 }

        } else {
            val newPairs = getNewPairs(pairs)
            getDistribution(depth-1, lastChar, newPairs)
        }
    }

    private fun getDistribution(depth : Int, lastChar: Char, pairs: Map<String, Long>): Map<Char, Long>  {
        return if (depth == 0) {
            pairs.entries.groupBy ({ it.key.first() }, {it.value })
                .mapValues { it.value.sum() + if (it.key == lastChar) 1 else 0 }

        } else {
            val newPairs = getNewPairs(pairs)
            getDistribution(depth-1, lastChar, newPairs)
        }
    }

    private fun getNewPairs(pairs: Map<String, Long>) =
        pairs.entries.flatMap { (it, value) ->
            listOf(
                Pair(it.first() + rules.getValue(it), value),
                Pair(rules.getValue(it) + it.last(), value)
            )
        }.groupBy({ it.first }, { it.second }).mapValues { it.value.sum() }

}
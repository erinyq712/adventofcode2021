package se.nyquist

class Rules(private val ruleMap: Map<String, Char>) {
    fun getDistribution(i: Int, s: Map.Entry<String, Long>): Map<Char, Long> {
        return getDistribution(i, s.key, s.value)
    }

    fun getDistribution(i: Int, s: String): Map<Char, Long> {
        return getDistribution(i, s, 1L)
    }

    fun getDistribution(i: Int, s: String, freq: Long): Map<Char, Long> {
        return if (i == 0) {
            getCharacterDistribution(s, freq)
        } else {
            val pairs = getPairs(s)
            val firstDistribution : Map<Char, Long> = getDistributionExcludingLast(i-1, pairs[0], freq)
            val secondDistribution : Map<Char, Long> = getDistribution(i-1, pairs[1], freq)
            mergeCharMaps(secondDistribution, firstDistribution)
        }
    }

    fun mergeCharMaps(
        secondDistribution: Map<Char, Long>,
        firstDistribution: Map<Char, Long>
    ) : Map<Char, Long> {
        val merged = firstDistribution.toMutableMap()
        secondDistribution.entries.forEach{
            if (merged.containsKey(it.key)) {
                merged[it.key] = firstDistribution.getValue(it.key) + secondDistribution.getValue(it.key)
            } else {
                merged[it.key] = secondDistribution.getValue(it.key)
            }
        }
        return merged
    }

    fun getDistributionExcludingLast(i: Int, s: String, freq: Long) : Map<Char, Long> {
        return if (i == 0) {
            getCharacterDistribution(s.substring(0, 1), freq)
        } else {
            val pairs = getPairs(s)
            val firstDistribution : Map<Char, Long> = getDistributionExcludingLast(i-1, pairs[0], freq)
            val secondDistribution : Map<Char, Long> = getDistributionExcludingLast(i-1, pairs[1], freq)
            mergeCharMaps(secondDistribution, firstDistribution)
        }
    }

    fun getDistributionExcludingLast(i: Int, s: Map.Entry<String, Long>): Map<Char, Long> {
        return getDistributionExcludingLast(i, s.key, s.value)
    }

    private fun getCharacterDistribution(
        s: String,
        freq: Long
    ) = s.toList().groupingBy { it }.fold(0L) { acc, _ -> acc + freq }

    private fun getPairs(key: String) : List<String> {
        val s = key[0] + ruleMap[key].toString() + key[1]
        return IntRange(0, s.lastIndex - 1).map { s.substring(it, it + 2) }.toList()
    }

    fun getValue(it: String): String {
        return ruleMap[it].toString()
    }
}
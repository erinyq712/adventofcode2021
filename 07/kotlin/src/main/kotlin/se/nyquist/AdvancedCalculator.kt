package se.nyquist

class AdvancedCalculator : Calculator {
    override fun getCost(i: Int, key: Int, count: Int) : Long {
        val diff = Math.abs(i - key).toLong()
        // Sum of 1 + 2 + ... diff
        val sum = if (diff % 2 == 0L) (diff+1)*diff/2 else diff*(diff-1)/2 + diff
        return sum * count
    }
}

// 1 + 2 + 3 , diff = 3, 3 * 2/2 + 3 = 6
// 1 + 2 + 3 + 4, diff = 4, 5 * 4 /2 = 10
// 1 + 2 + 3 + 4 + 5, diff = 5, 5 * 4 /2 + 5 = 15

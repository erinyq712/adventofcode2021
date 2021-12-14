package se.nyquist

class SimpleCalculator : Calculator {
    override fun getCost(i: Int, key: Int, count: Int) : Long {
        val diff = Math.abs(i - key).toLong()
        return count * diff
    }
}

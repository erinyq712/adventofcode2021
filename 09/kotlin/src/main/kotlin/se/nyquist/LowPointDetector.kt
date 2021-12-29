package se.nyquist

class LowPointDetector {
    fun detect(values: Array<Array<Int>>) : List<LowPoint> {
        val lowPoints = mutableListOf<LowPoint>()
        var prev = listOf<Int>()
        var next = values[0].toList()
        var current : List<Int>
        for (i in values.indices) {
            current = next
            next = if (i < values.size-1) values[i+1].toList() else listOf()
            detect(i, prev, current, next).forEach { lowPoints.add(it) }
            prev = current
        }
        return lowPoints
    }

    fun detect(row: Int, prevValuesArr: List<Int>, valuesArr: List<Int>, nextValuesArr: List<Int>) : Array<LowPoint> {

        val lengthInput = valuesArr.size
        val result = mutableListOf<LowPoint>()

        for(i in 0 until lengthInput) {
            var counter = 0
            counter = checkBackwards(i, valuesArr, counter)
            counter = checkForward(i, lengthInput, valuesArr, counter)
            counter = checkColumn(prevValuesArr, valuesArr, i, counter)
            counter = checkColumn(nextValuesArr, valuesArr, i, counter)

            if (counter == 4) {
                result.add(LowPoint(i, row, valuesArr[i]))
            }
        }
        return result.toTypedArray()
    }

    private fun checkColumn(
        prevValuesArr: List<Int>,
        valuesArr: List<Int>,
        i: Int,
        counter: Int
    ): Int {
        var counter1 = counter
        if (prevValuesArr.isNotEmpty()) {
            if (valuesArr[i] < prevValuesArr[i]) {
                counter1++
            }
        } else {
            counter1++
        }
        return counter1
    }

    private fun checkForward(
        i: Int,
        lengthInput: Int,
        valuesArr: List<Int>,
        counter: Int
    ): Int {
        var counter1 = counter
        if (i < lengthInput - 1) {
            if (valuesArr[i] < valuesArr[i + 1]) {
                counter1++
            }
        } else {
            counter1++
        }
        return counter1
    }

    private fun checkBackwards(
        i: Int,
        valuesArr: List<Int>,
        counter: Int
    ): Int {
        var counter1 = counter
        if (i > 0) {
            if (valuesArr[i - 1] > valuesArr[i]) {
                counter1++
            }
        } else {
            counter1++
        }
        return counter1
    }
}
package se.nyquist

data class Basin(val points: List<Pair<Int,Int>>) {
    val size : Int
        get() =  points.size
}

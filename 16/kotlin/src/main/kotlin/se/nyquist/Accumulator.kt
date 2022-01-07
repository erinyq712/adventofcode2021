package se.nyquist

class Accumulator() {
    var counter = 0

    fun incrementBy(inc: Int) {
        counter += inc
    }

    fun start(encoder : BitsEncoder) {
        encoder.addAccumulator(this)
    }

    fun stop(encoder: BitsEncoder) {
        encoder.removeAccumulator(this)
    }
}

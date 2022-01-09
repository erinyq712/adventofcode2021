package se.nyquist

data class ProbeState(val position: Pair<Int, Int>, val velocity: Pair<Int, Int>) {
    fun nextPosition() : ProbeState {
        val newPos = Pair(position.first + velocity.first, position.second + velocity.second)
        val newVelocity = Pair(
            if (velocity.first > 1) velocity.first - 1 else if (velocity.first < 0)  velocity.first + 1 else 0,
            velocity.second - 1)
        return ProbeState(newPos, newVelocity)
    }

    override fun toString(): String {
        return "ProbeState(position=$position, velocity=$velocity)"
    }

}
